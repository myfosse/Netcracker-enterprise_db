package by.netcracker.enterprisedb.service.mockito;

import by.netcracker.enterprisedb.dao.entity.News;
import by.netcracker.enterprisedb.dao.repository.NewsRepository;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.dto.model.NewsDTO;
import by.netcracker.enterprisedb.service.NewsService;
import by.netcracker.enterprisedb.service.impl.NewsServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/** @author Andrey Egorov */
@ContextConfiguration(classes = {NewsServiceImpl.class})
public class NewsServiceTest extends BaseServiceTest<News, NewsDTO> {

  public NewsServiceTest() throws IOException {
    list = Arrays.asList(mapper.readValue(getAbsoluteFile("news"), News[].class));
    listDTO = Arrays.asList(mapper.readValue(getAbsoluteFile("newsDTO"), NewsDTO[].class));
    input = list.get(0);
    inputDTO = listDTO.get(0);
  }

  private final NewsDTO outputNewsDTO =
      new NewsDTO(
              new EmployeeDTO(
                      "Andrey", "Egorov", null, 100, 1234567890, null, null), "Title", "Text", null);

  @Autowired private NewsService newsService;

  @MockBean private NewsRepository newsRepository;

  @Test
  public void testSave() {
    when(newsRepository.save(input)).thenReturn(input);

    assertEquals(outputNewsDTO, newsService.save(inputDTO));
  }

  @Test
  public void testUpdate() {
    when(newsRepository.save(input)).thenReturn(input);

    assertEquals(outputNewsDTO, newsService.update(inputDTO));
  }

  @Test
  public void testFindById() {
    Long id = 1L;

    doReturn(Optional.of(input)).when(newsRepository).findById(id);

    assertEquals(outputNewsDTO, newsService.findById(id));
  }

  @Test
  public void testGetAll() {
    doReturn(list).when(newsRepository).findAll();

    assertEquals(listDTO, newsService.getAll());
  }

  @Test
  public void testDeleteById() {
    Long id = 1L;

    doNothing().when(newsRepository).deleteById(id);

    newsService.deleteById(id);

    verify(newsRepository).deleteById(id);
  }
}
