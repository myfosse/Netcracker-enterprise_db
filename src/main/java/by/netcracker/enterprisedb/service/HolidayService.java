package by.netcracker.enterprisedb.service;

import by.netcracker.enterprisedb.dto.model.HolidayDTO;

import java.util.List;

public interface HolidayService extends BaseService<HolidayDTO> {

  List<HolidayDTO> getAllByEmployeeId(final Long employeeId);

  List<HolidayDTO> getAllFinishedHolidays();

  List<HolidayDTO> getAllNotFinishedHolidays();
}
