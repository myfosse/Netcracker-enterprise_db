package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.converter.dto.RequestConverterDTO;
import by.netcracker.enterprisedb.dao.repository.RequestRepository;
import by.netcracker.enterprisedb.dto.model.RequestDTO;
import by.netcracker.enterprisedb.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService {

  private final RequestRepository requestRepository;

  @Autowired
  public RequestServiceImpl(final RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  @Override
  @Transactional
  public RequestDTO save(final RequestDTO request) {
    log.info("Save request to DB");
    return RequestConverterDTO.convertEntityToDTO(
        requestRepository.save(RequestConverterDTO.convertDTOToEntity(request)));
  }

  @Override
  @Transactional
  public RequestDTO update(final RequestDTO request) {
    log.info("Update request in DB");
    return RequestConverterDTO.convertEntityToDTO(
        requestRepository.save(RequestConverterDTO.convertDTOToEntity(request)));
  }

  @Override
  public RequestDTO findById(final Long id) {
    log.info("Find by id request from DB");
    return RequestConverterDTO.convertEntityToDTO(
        requestRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Error! Request not found")));
  }

  @Override
  public List<RequestDTO> getAll() {
    log.info("Find all requests from DB");
    return RequestConverterDTO.convertListEntityToDTO(requestRepository.findAll());
  }

  @Override
  public List<RequestDTO> getAllByEmployeeId(final Long employeeId) {
    log.info("Find all holidays by employee id from DB");
    return RequestConverterDTO.convertListEntityToDTO(
        requestRepository.getAllByEmployeeId(employeeId));
  }

  @Override
  public List<RequestDTO> getAllByAdminId(final Long adminId) {
    log.info("Find all holidays by admin id from DB");
    return RequestConverterDTO.convertListEntityToDTO(requestRepository.getAllByAdminId(adminId));
  }

  @Override
  @Transactional
  public void deleteById(final Long id) {
    log.info("Delete request by id from DB");
    requestRepository.deleteById(id);
  }
}
