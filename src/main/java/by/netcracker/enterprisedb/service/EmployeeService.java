package by.netcracker.enterprisedb.service;

import by.netcracker.enterprisedb.dao.entity.Employee;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;

public interface EmployeeService extends BaseService<EmployeeDTO> {

  boolean existsByEmail(final String email);

  EmployeeDTO save(final Employee employee);

  EmployeeDTO update(final Employee employee);
}
