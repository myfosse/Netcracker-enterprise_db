package by.netcracker.enterprisedb.service;

import by.netcracker.enterprisedb.dto.model.CareerDTO;

import java.util.List;

public interface CareerService extends BaseService<CareerDTO> {

  List<CareerDTO> getAllByEmployeeId(final Long employeeId);
}
