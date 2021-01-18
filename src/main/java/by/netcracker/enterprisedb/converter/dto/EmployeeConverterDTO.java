package by.netcracker.enterprisedb.converter.dto;

import by.netcracker.enterprisedb.dao.entity.Employee;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeConverterDTO {

  public static EmployeeDTO convertEntityToDTO(final Employee employeeEntity) {
    EmployeeDTO employeeDTO =
        EmployeeDTO.builder()
            .name(employeeEntity.getName())
            .surname(employeeEntity.getSurname())
            .amountOfHolidays(employeeEntity.getAmountOfHolidays())
            .birthdate(employeeEntity.getBirthdate())
            .insuranceNumber(employeeEntity.getInsuranceNumber())
            .roles(employeeEntity.getRoles())
            .email(employeeEntity.getEmail())
            .password(employeeEntity.getPassword())
            .build();
    employeeDTO.setId(employeeEntity.getId());
    return employeeDTO;
  }

  public static Employee convertDTOToEntity(final EmployeeDTO employeeDTO) {
    Employee employee =
        Employee.builder()
            .name(employeeDTO.getName())
            .surname(employeeDTO.getSurname())
            .amountOfHolidays(employeeDTO.getAmountOfHolidays())
            .birthdate(employeeDTO.getBirthdate())
            .insuranceNumber(employeeDTO.getInsuranceNumber())
            .roles(employeeDTO.getRoles())
            .email(employeeDTO.getEmail())
            .password(employeeDTO.getPassword())
            .build();
    employee.setId(employeeDTO.getId());
    return employee;
  }

  public static List<EmployeeDTO> convertListEntityToDTO(final List<Employee> employeeList) {
    return employeeList.stream()
        .map(EmployeeConverterDTO::convertEntityToDTO)
        .collect(Collectors.toList());
  }

  public static List<Employee> convertListDTOToEntity(final List<EmployeeDTO> employeeDTOList) {
    return employeeDTOList.stream()
        .map(EmployeeConverterDTO::convertDTOToEntity)
        .collect(Collectors.toList());
  }
}
