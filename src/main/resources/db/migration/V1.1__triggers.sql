CREATE
    OR REPLACE FUNCTION public.check_before_insert_or_update()
    RETURNS trigger
    LANGUAGE plpgsql
AS
$function$
DECLARE
    employee_amount_of_holidays integer;
begin
    select amount_of_holidays
    from employee
    into employee_amount_of_holidays where employee.id = new.emp_id;
    if
        (employee_amount_of_holidays < new.end_date - new.start_date + 1) then
        RAISE EXCEPTION 'Amount of holiays less than you want';
    end if;
    return new;
END;
$function$
;

CREATE
    OR REPLACE FUNCTION public.holiday_after_insert()
    RETURNS trigger
    LANGUAGE plpgsql
AS
$function$
DECLARE
    employee_amount_of_holidays integer;
begin
    select amount_of_holidays
    from employee
    into employee_amount_of_holidays where employee.id = new.emp_id;
    update employee
    set amount_of_holidays = employee_amount_of_holidays - (new.end_date - new.start_date) - 1
    where id = new.emp_id;
    return new;
END;
$function$
;

CREATE
    OR REPLACE FUNCTION public.holiday_after_update()
    RETURNS trigger
    LANGUAGE plpgsql
AS
$function$
DECLARE
    employee_amount_of_holidays integer;
begin
    select amount_of_holidays
    from employee
    into employee_amount_of_holidays where employee.id = new.emp_id;
    if
        (old.end_date = new.end_date and old.start_date != new.start_date) then
        update employee
        set amount_of_holidays = employee_amount_of_holidays - (old.start_date - new.start_date)
        where id = new.emp_id;
    end if;
    if
        (old.end_date != new.end_date and old.start_date = new.start_date) then
        update employee
        set amount_of_holidays = employee_amount_of_holidays - (new.end_date - old.end_date)
        where id = new.emp_id;
    end if;
    if
        (old.end_date != new.end_date and old.start_date != new.start_date) then
        update employee
        set amount_of_holidays = employee_amount_of_holidays - (new.end_date - old.end_date) -
                                 (old.start_date - new.start_date)
        where id = new.emp_id;
    end if;
    return new;
END;
$function$
;


create trigger holiday_before_insert_or_update
    before
        insert
        or
        update
    on
        public.holiday
    for each row
execute function check_before_insert_or_update();


create trigger holiday_after_insert
    after
        insert
    on
        public.holiday
    for each row
execute function holiday_after_insert();

create trigger holiday_after_update
    after
        update
    on
        public.holiday
    for each row
execute function holiday_after_update();