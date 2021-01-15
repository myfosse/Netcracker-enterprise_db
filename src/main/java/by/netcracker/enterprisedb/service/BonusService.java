package by.netcracker.enterprisedb.service;

import by.netcracker.enterprisedb.dto.model.BonusDTO;

import java.util.List;

public interface BonusService extends BaseService<BonusDTO> {

  List<BonusDTO> getAllByEmployeeId(final Long employeeId);
}
