package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.converter.dto.NewsConverterDTO;
import by.netcracker.enterprisedb.dao.repository.NewsRepository;
import by.netcracker.enterprisedb.dto.model.NewsDTO;
import by.netcracker.enterprisedb.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class NewsServiceImpl implements NewsService {

  private final NewsRepository newsRepository;

  @Autowired
  public NewsServiceImpl(final NewsRepository newsRepository) {
    this.newsRepository = newsRepository;
  }

  @Override
  @Transactional
  public NewsDTO save(final NewsDTO news) {
    log.info("Save news to DB");
    newsRepository.save(NewsConverterDTO.convertDTOToEntity(news));
    return news;
  }

  @Override
  @Transactional
  public NewsDTO update(final NewsDTO news) {
    log.info("Update news in DB");
    newsRepository.save(NewsConverterDTO.convertDTOToEntity(news));
    return news;
  }

  @Override
  public NewsDTO findById(final Long id) {
    log.info("Find by id news from DB");
    return NewsConverterDTO.convertEntityToDTO(
        newsRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Error! News not found")));
  }

  @Override
  public List<NewsDTO> getAll() {
    log.info("Find all news from DB");
    return NewsConverterDTO.convertListEntityToDTO(newsRepository.findAll());
  }

  @Override
  public List<NewsDTO> getAllByAdminId(final Long adminId) {
    log.info("Find all news by employee id from DB");
    return NewsConverterDTO.convertListEntityToDTO(newsRepository.getAllByAdminId(adminId));
  }

  @Override
  @Transactional
  public void deleteById(final Long id) {
    log.info("Delete news by id from DB");
    newsRepository.deleteById(id);
  }
}
