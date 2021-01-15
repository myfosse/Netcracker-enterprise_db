package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.converter.dto.CareerConverterDTO;
import by.netcracker.enterprisedb.dao.repository.CareerRepository;
import by.netcracker.enterprisedb.dto.model.CareerDTO;
import by.netcracker.enterprisedb.service.CareerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class CareerServiceImpl implements CareerService {

  private final CareerRepository careerRepository;

  @Autowired
  public CareerServiceImpl(CareerRepository careerRepository) {
    this.careerRepository = careerRepository;
  }

  @Override
  @Transactional
  public CareerDTO save(final CareerDTO career) {
    log.info("Save career to DB");
    careerRepository.save(CareerConverterDTO.convertDTOToEntity(career));
    return career;
  }

  @Override
  @Transactional
  public CareerDTO update(final CareerDTO career) {
    log.info("Update career in DB");
    careerRepository.save(CareerConverterDTO.convertDTOToEntity(career));
    return career;
  }

  @Override
  public CareerDTO findById(final Long id) {
    log.info("Find by id career from DB");
    return CareerConverterDTO.convertEntityToDTO(
        careerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Error! Career not found")));
  }

  @Override
  public List<CareerDTO> getAll() {
    log.info("Find all careers from DB");
    return CareerConverterDTO.convertListEntityToDTO(careerRepository.findAll());
  }

  @Override
  public List<CareerDTO> getAllByEmployeeId(final Long employeeId) {
    log.info("Find all careers by employee id from DB");
    return CareerConverterDTO.convertListEntityToDTO(
        careerRepository.getAllByEmployeeId(employeeId));
  }

  @Override
  @Transactional
  public void deleteById(final Long id) {
    log.info("Delete career by id from DB");
    careerRepository.deleteById(id);
  }
}
