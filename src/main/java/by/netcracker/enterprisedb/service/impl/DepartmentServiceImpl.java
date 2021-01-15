package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.converter.dto.DepartmentConverterDTO;
import by.netcracker.enterprisedb.dao.repository.DepartmentRepository;
import by.netcracker.enterprisedb.dto.model.DepartmentDTO;
import by.netcracker.enterprisedb.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Autowired
  public DepartmentServiceImpl(final DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  @Transactional
  public DepartmentDTO save(final DepartmentDTO department) {
    log.info("Save department to DB");
    departmentRepository.save(DepartmentConverterDTO.convertDTOToEntity(department));
    return department;
  }

  @Override
  @Transactional
  public DepartmentDTO update(final DepartmentDTO department) {
    log.info("Update department in DB");
    departmentRepository.save(DepartmentConverterDTO.convertDTOToEntity(department));
    return department;
  }

  @Override
  public DepartmentDTO findById(final Long id) {
    log.info("Find by id department from DB");
    return DepartmentConverterDTO.convertEntityToDTO(
        departmentRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Error! Department not found")));
  }

  @Override
  public List<DepartmentDTO> getAll() {
    log.info("Find all departments from DB");
    return DepartmentConverterDTO.convertListEntityToDTO(departmentRepository.findAll());
  }

  @Override
  @Transactional
  public void deleteById(final Long id) {
    log.info("Delete department by id from DB");
    departmentRepository.deleteById(id);
  }
}
