package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.converter.dto.EmployeeConverterDTO;
import by.netcracker.enterprisedb.dao.entity.Employee;
import by.netcracker.enterprisedb.dao.repository.EmployeeRepository;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public boolean existsByEmail(final String email) {
    return employeeRepository.existsByEmail(email);
  }

  @Transactional
  public EmployeeDTO save(final Employee employee) {
    log.info("Save employee to DB");
    employeeRepository.save(employee);
    return EmployeeConverterDTO.convertEntityToDTO(employee);
  }

  @Transactional
  public EmployeeDTO update(final Employee employee) {
    log.info("Update employee in DB");
    employeeRepository.save(employee);
    return EmployeeConverterDTO.convertEntityToDTO(employee);
  }

  @Override
  public EmployeeDTO save(EmployeeDTO object) {
    log.info("Save news to DB");
    employeeRepository.save(EmployeeConverterDTO.convertDTOToEntity(object));
    return object;
  }

  @Override
  public EmployeeDTO update(EmployeeDTO object) {
    log.info("Save news to DB");
    employeeRepository.save(EmployeeConverterDTO.convertDTOToEntity(object));
    return object;
  }

  @Override
  public EmployeeDTO findById(final Long id) {
    log.info("Find by id employee from DB");
    return EmployeeConverterDTO.convertEntityToDTO(
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Error! Employee not found")));
  }

  @Override
  public List<EmployeeDTO> getAll() {
    log.info("Find all employees from DB");
    return EmployeeConverterDTO.convertListEntityToDTO(employeeRepository.findAll());
  }

  @Override
  @Transactional
  public void deleteById(final Long id) {
    log.info("Delete employee by id from DB");
    employeeRepository.deleteById(id);
  }
}
