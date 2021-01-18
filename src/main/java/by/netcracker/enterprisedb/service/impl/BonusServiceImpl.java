package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.converter.dto.BonusConverterDTO;
import by.netcracker.enterprisedb.dao.repository.BonusRepository;
import by.netcracker.enterprisedb.dto.model.BonusDTO;
import by.netcracker.enterprisedb.service.BonusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class BonusServiceImpl implements BonusService {

  private final BonusRepository bonusRepository;

  @Autowired
  public BonusServiceImpl(final BonusRepository bonusRepository) {
    this.bonusRepository = bonusRepository;
  }

  @Override
  @Transactional
  public BonusDTO save(final BonusDTO bonus) {
    log.info("Save bonus to DB");
    return BonusConverterDTO.convertEntityToDTO(
        bonusRepository.save(BonusConverterDTO.convertDTOToEntity(bonus)));
  }

  @Override
  @Transactional
  public BonusDTO update(final BonusDTO bonus) {
    log.info("Update bonus in DB");
    return BonusConverterDTO.convertEntityToDTO(
        bonusRepository.save(BonusConverterDTO.convertDTOToEntity(bonus)));
  }

  @Override
  public BonusDTO findById(final Long id) {
    log.info("Find by id bonus from DB");
    return BonusConverterDTO.convertEntityToDTO(
        bonusRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Error! Bonus not found")));
  }

  @Override
  public List<BonusDTO> getAll() {
    log.info("Find all bonuses from DB");
    return BonusConverterDTO.convertListEntityToDTO(bonusRepository.findAll());
  }

  @Override
  public List<BonusDTO> getAllByEmployeeId(final Long employeeId) {
    log.info("Find all bonuses by employee id from DB");
    return BonusConverterDTO.convertListEntityToDTO(bonusRepository.getAllByEmployeeId(employeeId));
  }

  @Override
  @Transactional
  public void deleteById(final Long id) {
    log.info("Delete bonus by id from DB");
    bonusRepository.deleteById(id);
  }
}
