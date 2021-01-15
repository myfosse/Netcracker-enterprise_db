package by.netcracker.enterprisedb.service;

import by.netcracker.enterprisedb.dto.model.NewsDTO;

import java.util.List;

public interface NewsService extends BaseService<NewsDTO> {

  List<NewsDTO> getAllByAdminId(final Long adminId);
}
