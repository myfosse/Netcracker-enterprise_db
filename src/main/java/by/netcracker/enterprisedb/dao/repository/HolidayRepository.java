package by.netcracker.enterprisedb.dao.repository;

import by.netcracker.enterprisedb.dao.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

  List<Holiday> getAllByEmployeeId(final Long employeeId);

  @Query(value = "select * from holiday where end_date < now()", nativeQuery = true)
  List<Holiday> getAllFinishedHolidays();

  @Query(value = "select * from holiday where end_date >= now()", nativeQuery = true)
  List<Holiday> getAllNotFinishedHolidays();
}
