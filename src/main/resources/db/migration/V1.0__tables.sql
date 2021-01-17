CREATE TABLE public."role"
(
    id     serial  NOT NULL,
    "name" varchar NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);

CREATE TABLE public.employee
(
    id                 serial  NOT NULL,
    "name"             varchar NOT NULL,
    surname            varchar NOT NULL,
    birthdate          date    NOT NULL,
    amount_of_holidays int4    NOT NULL,
    insurance_number   int4    NOT NULL,
    email              varchar NOT NULL,
    password           varchar NOT NULL,
    CONSTRAINT employee_amount_of_holidays_check CHECK ((amount_of_holidays >= 0)),
    CONSTRAINT employee_birthdate_check CHECK ((birthdate > '1900-01-01'::date)),
    CONSTRAINT employee_pk PRIMARY KEY (id)
);

CREATE TABLE public.user_roles
(
    user_id int8 NOT NULL,
    role_id int8 NOT NULL,
    CONSTRAINT user_roles_role_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_roles_user_pkey FOREIGN KEY (user_id) REFERENCES employee (id),
    CONSTRAINT user_roles_role_fkey FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE public.department
(
    id      serial  NOT NULL,
    "name"  varchar NOT NULL,
    country varchar NOT NULL,
    city    varchar NOT NULL,
    CONSTRAINT department_pk PRIMARY KEY (id)
);

CREATE TABLE public."position"
(
    id         serial  NOT NULL,
    specialty  varchar NOT NULL,
    min_salary int4    NOT NULL,
    CONSTRAINT position_min_salary_check CHECK ((min_salary > 0)),
    CONSTRAINT position_pk PRIMARY KEY (id)
);

CREATE TABLE public.bonus
(
    id      serial NOT NULL,
    emp_id  int4   NOT NULL,
    "month" int4   NOT NULL,
    "year"  int4   NOT NULL,
    value   int4   NOT NULL,
    CONSTRAINT bonus_emp_id_check CHECK ((emp_id > 0)),
    CONSTRAINT bonus_month_check CHECK (((month > 0) AND (month < 13))),
    CONSTRAINT bonus_pk PRIMARY KEY (id),
    CONSTRAINT bonus_value_check CHECK ((value > 0)),
    CONSTRAINT bonus_year_check CHECK (((year > 1900) AND (year < 3000))),
    CONSTRAINT bonus_fk FOREIGN KEY (emp_id) REFERENCES employee (id)
);

CREATE TABLE public.career
(
    id         serial NOT NULL,
    emp_id     int4   NOT NULL,
    dept_id    int4   NOT NULL,
    pos_id     int4   NOT NULL,
    start_date date   NOT NULL,
    end_date   date   NOT NULL,
    CONSTRAINT career_dates_check CHECK ((start_date <= end_date)),
    CONSTRAINT career_dept_id_check CHECK ((dept_id > 0)),
    CONSTRAINT career_emp_id_check CHECK ((emp_id > 0)),
    CONSTRAINT career_pk PRIMARY KEY (id),
    CONSTRAINT career_pos_id_check CHECK ((pos_id > 0)),
    CONSTRAINT career_fk FOREIGN KEY (emp_id) REFERENCES employee (id),
    CONSTRAINT career_fk_1 FOREIGN KEY (dept_id) REFERENCES department (id),
    CONSTRAINT career_fk_2 FOREIGN KEY (pos_id) REFERENCES "position" (id)
);

CREATE TABLE public.holiday
(
    id         serial NOT NULL,
    emp_id     int4   NOT NULL,
    start_date date   NOT NULL,
    end_date   date   NOT NULL,
    CONSTRAINT holiday_dates_check CHECK ((start_date <= end_date)),
    CONSTRAINT holiday_emp_id_check CHECK ((emp_id > 0)),
    CONSTRAINT holiday_pk PRIMARY KEY (id),
    CONSTRAINT holiday_fk FOREIGN KEY (emp_id) REFERENCES employee (id)
);

CREATE TABLE public.news
(
    id        serial  NOT NULL,
    adm_id    int4    NOT NULL,
    title     varchar NOT NULL,
    "text"    varchar NOT NULL,
    post_date date    NOT NULL DEFAULT now(),
    CONSTRAINT news_adm_id_check CHECK ((adm_id > 0)),
    CONSTRAINT news_pk PRIMARY KEY (id),
    CONSTRAINT news_fk FOREIGN KEY (adm_id) REFERENCES employee (id)
);

CREATE TABLE public.request
(
    id        serial  NOT NULL,
    emp_id    int4    NOT NULL,
    adm_id    int4    NOT NULL,
    title     varchar NOT NULL,
    "text"    varchar NOT NULL,
    agreed    bool    NOT NULL DEFAULT false,
    send_date date    NOT NULL DEFAULT now(),
    CONSTRAINT request_adm_id_check CHECK ((adm_id > 0)),
    CONSTRAINT request_emp_id_check CHECK ((emp_id > 0)),
    CONSTRAINT request_pk PRIMARY KEY (id),
    CONSTRAINT request_fk FOREIGN KEY (emp_id) REFERENCES employee (id),
    CONSTRAINT request_fk_1 FOREIGN KEY (adm_id) REFERENCES employee (id)
);