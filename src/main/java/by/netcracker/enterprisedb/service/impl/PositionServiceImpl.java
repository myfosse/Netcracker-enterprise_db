package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.converter.dto.NewsConverterDTO;
import by.netcracker.enterprisedb.converter.dto.PositionConverterDTO;
import by.netcracker.enterprisedb.dao.repository.PositionRepository;
import by.netcracker.enterprisedb.dto.model.PositionDTO;
import by.netcracker.enterprisedb.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class PositionServiceImpl implements PositionService {

  private final PositionRepository positionRepository;

  @Autowired
  public PositionServiceImpl(final PositionRepository positionRepository) {
    this.positionRepository = positionRepository;
  }

  @Override
  @Transactional
  public PositionDTO save(final PositionDTO position) {
    log.info("Save position to DB");
    return PositionConverterDTO.convertEntityToDTO(
        positionRepository.save(PositionConverterDTO.convertDTOToEntity(position)));
  }

  @Override
  @Transactional
  public PositionDTO update(final PositionDTO position) {
    log.info("Update position in DB");
    return PositionConverterDTO.convertEntityToDTO(
        positionRepository.save(PositionConverterDTO.convertDTOToEntity(position)));
  }

  @Override
  public PositionDTO findById(final Long id) {
    log.info("Find by id position from DB");
    return PositionConverterDTO.convertEntityToDTO(
        positionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Error! Position not found")));
  }

  @Override
  public List<PositionDTO> getAll() {
    log.info("Find all positions from DB");
    return PositionConverterDTO.convertListEntityToDTO(positionRepository.findAll());
  }

  @Override
  @Transactional
  public void deleteById(final Long id) {
    log.info("Delete position by id from DB");
    positionRepository.deleteById(id);
  }
}
