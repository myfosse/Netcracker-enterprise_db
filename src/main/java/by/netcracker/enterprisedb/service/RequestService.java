package by.netcracker.enterprisedb.service;

import by.netcracker.enterprisedb.dto.model.RequestDTO;

import java.util.List;

public interface RequestService extends BaseService<RequestDTO> {

  List<RequestDTO> getAllByEmployeeId(final Long employeeId);

  List<RequestDTO> getAllByAdminId(final Long adminId);
}
