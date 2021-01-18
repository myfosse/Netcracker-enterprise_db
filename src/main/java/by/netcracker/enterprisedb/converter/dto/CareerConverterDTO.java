package by.netcracker.enterprisedb.converter.dto;

import by.netcracker.enterprisedb.dao.entity.Career;
import by.netcracker.enterprisedb.dto.model.CareerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CareerConverterDTO {

  public static CareerDTO convertEntityToDTO(final Career careerEntity) {
    CareerDTO careerDTO =
        CareerDTO.builder()
            .employee(EmployeeConverterDTO.convertEntityToDTO(careerEntity.getEmployee()))
            .department(DepartmentConverterDTO.convertEntityToDTO(careerEntity.getDepartment()))
            .position(PositionConverterDTO.convertEntityToDTO(careerEntity.getPosition()))
            .startDate(careerEntity.getStartDate())
            .endDate(careerEntity.getEndDate())
            .empId(careerEntity.getEmployee().getId())
            .deptId(careerEntity.getDepartment().getId())
            .posId(careerEntity.getPosition().getId())
            .build();
    careerDTO.setId(careerEntity.getId());
    return careerDTO;
  }

  public static Career convertDTOToEntity(final CareerDTO careerDTO) {
    Career career =
        Career.builder()
            .employee(EmployeeConverterDTO.convertDTOToEntity(careerDTO.getEmployee()))
            .department(DepartmentConverterDTO.convertDTOToEntity(careerDTO.getDepartment()))
            .position(PositionConverterDTO.convertDTOToEntity(careerDTO.getPosition()))
            .startDate(careerDTO.getStartDate())
            .endDate(careerDTO.getEndDate())
            .build();
    career.setId(careerDTO.getId());
    return career;
  }

  public static List<CareerDTO> convertListEntityToDTO(final List<Career> careerList) {
    return careerList.stream()
        .map(CareerConverterDTO::convertEntityToDTO)
        .collect(Collectors.toList());
  }

  public static List<Career> convertListDTOToEntity(final List<CareerDTO> careerDTOList) {
    return careerDTOList.stream()
        .map(CareerConverterDTO::convertDTOToEntity)
        .collect(Collectors.toList());
  }
}
