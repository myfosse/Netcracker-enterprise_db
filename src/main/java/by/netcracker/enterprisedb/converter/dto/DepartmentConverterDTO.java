package by.netcracker.enterprisedb.converter.dto;

import by.netcracker.enterprisedb.dao.entity.Department;
import by.netcracker.enterprisedb.dto.model.DepartmentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentConverterDTO {

  public static DepartmentDTO convertEntityToDTO(final Department departmentEntity) {
    DepartmentDTO departmentDTO =
        DepartmentDTO.builder()
            .name(departmentEntity.getName())
            .city(departmentEntity.getCity())
            .country(departmentEntity.getCountry())
            .build();
    departmentDTO.setId(departmentEntity.getId());
    return departmentDTO;
  }

  public static Department convertDTOToEntity(final DepartmentDTO departmentDTO) {
    Department department =
        Department.builder()
            .name(departmentDTO.getName())
            .city(departmentDTO.getCity())
            .country(departmentDTO.getCountry())
            .build();
    department.setId(departmentDTO.getId());
    return department;
  }

  public static List<DepartmentDTO> convertListEntityToDTO(final List<Department> departmentList) {
    return departmentList.stream()
        .map(DepartmentConverterDTO::convertEntityToDTO)
        .collect(Collectors.toList());
  }

  public static List<Department> convertListDTOToEntity(
      final List<DepartmentDTO> departmentDTOList) {
    return departmentDTOList.stream()
        .map(DepartmentConverterDTO::convertDTOToEntity)
        .collect(Collectors.toList());
  }
}
