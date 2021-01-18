package by.netcracker.enterprisedb.converter.dto;

import by.netcracker.enterprisedb.dao.entity.Holiday;
import by.netcracker.enterprisedb.dto.model.HolidayDTO;

import java.util.List;
import java.util.stream.Collectors;

public class HolidayConverterDTO {

  public static HolidayDTO convertEntityToDTO(final Holiday holidayEntity) {
    HolidayDTO holidayDTO =
        HolidayDTO.builder()
            .employee(EmployeeConverterDTO.convertEntityToDTO(holidayEntity.getEmployee()))
            .startDate(holidayEntity.getStartDate())
            .endDate(holidayEntity.getEndDate())
            .empId(holidayEntity.getEmployee().getId())
            .build();
    holidayDTO.setId(holidayEntity.getId());
    return holidayDTO;
  }

  public static Holiday convertDTOToEntity(final HolidayDTO holidayDTO) {
    Holiday holiday =
        Holiday.builder()
            .employee(EmployeeConverterDTO.convertDTOToEntity(holidayDTO.getEmployee()))
            .startDate(holidayDTO.getStartDate())
            .endDate(holidayDTO.getEndDate())
            .build();
    holiday.setId(holidayDTO.getId());
    return holiday;
  }

  public static List<HolidayDTO> convertListEntityToDTO(final List<Holiday> holidayList) {
    return holidayList.stream()
        .map(HolidayConverterDTO::convertEntityToDTO)
        .collect(Collectors.toList());
  }

  public static List<Holiday> convertListDTOToEntity(final List<HolidayDTO> holidayDTOList) {
    return holidayDTOList.stream()
        .map(HolidayConverterDTO::convertDTOToEntity)
        .collect(Collectors.toList());
  }
}
