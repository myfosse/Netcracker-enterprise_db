package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.converter.dto.HolidayConverterDTO;
import by.netcracker.enterprisedb.dao.repository.HolidayRepository;
import by.netcracker.enterprisedb.dto.model.HolidayDTO;
import by.netcracker.enterprisedb.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class HolidayServiceImpl implements HolidayService {

  private final HolidayRepository holidayRepository;

  @Autowired
  public HolidayServiceImpl(final HolidayRepository holidayRepository) {
    this.holidayRepository = holidayRepository;
  }

  @Override
  @Transactional
  public HolidayDTO save(final HolidayDTO holiday) {
    log.info("Save holiday to DB");
    holidayRepository.save(HolidayConverterDTO.convertDTOToEntity(holiday));
    return holiday;
  }

  @Override
  @Transactional
  public HolidayDTO update(final HolidayDTO holiday) {
    log.info("Update holiday in DB");
    holidayRepository.save(HolidayConverterDTO.convertDTOToEntity(holiday));
    return holiday;
  }

  @Override
  public HolidayDTO findById(final Long id) {
    log.info("Find by id holiday from DB");
    return HolidayConverterDTO.convertEntityToDTO(
        holidayRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Error! Holiday not found")));
  }

  @Override
  public List<HolidayDTO> getAll() {
    log.info("Find all holidays from DB");
    return HolidayConverterDTO.convertListEntityToDTO(holidayRepository.findAll());
  }

  @Override
  public List<HolidayDTO> getAllByEmployeeId(final Long employeeId) {
    log.info("Find all holidays by employee id from DB");
    return HolidayConverterDTO.convertListEntityToDTO(
        holidayRepository.getAllByEmployeeId(employeeId));
  }

  @Override
  public List<HolidayDTO> getAllFinishedHolidays() {
    log.info("Find all finished holidays from DB");
    return HolidayConverterDTO.convertListEntityToDTO(holidayRepository.getAllFinishedHolidays());
  }

  @Override
  public List<HolidayDTO> getAllNotFinishedHolidays() {
    log.info("Find all not finished holidays from DB");
    return HolidayConverterDTO.convertListEntityToDTO(
        holidayRepository.getAllNotFinishedHolidays());
  }

  @Override
  @Transactional
  public void deleteById(final Long id) {
    log.info("Delete employee by id from DB");
    holidayRepository.deleteById(id);
  }
}
