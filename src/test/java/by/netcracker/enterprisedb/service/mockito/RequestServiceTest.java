package by.netcracker.enterprisedb.service.mockito;

import by.netcracker.enterprisedb.dao.entity.Request;
import by.netcracker.enterprisedb.dao.repository.RequestRepository;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.dto.model.RequestDTO;
import by.netcracker.enterprisedb.service.RequestService;
import by.netcracker.enterprisedb.service.impl.RequestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/** @author Andrey Egorov */
@ContextConfiguration(classes = {RequestServiceImpl.class})
public class RequestServiceTest extends BaseServiceTest<Request, RequestDTO> {

  public RequestServiceTest() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    list = Arrays.asList(mapper.readValue(getAbsoluteFile("request"), Request[].class));
    listDTO = Arrays.asList(mapper.readValue(getAbsoluteFile("requestDTO"), RequestDTO[].class));
    input = list.get(0);
    inputDTO = listDTO.get(0);
  }

  private final RequestDTO outputRequestDTO =
      new RequestDTO(
          new EmployeeDTO("String", "String", "String@mail.ru", null, 1, 1, "String", null, null),
          new EmployeeDTO("String", "String", "String@mail.ru", null, 1, 1, "String", null, null),
          1L,
          1L,
          "Title",
          "Text",
          null);

  @Autowired private RequestService requestService;

  @MockBean private RequestRepository requestRepository;

  @Test
  public void testSave() {
    when(requestRepository.save(input)).thenReturn(input);

    assertEquals(outputRequestDTO, requestService.save(inputDTO));
  }

  @Test
  public void testUpdate() {
    when(requestRepository.save(input)).thenReturn(input);

    assertEquals(outputRequestDTO, requestService.update(inputDTO));
  }

  @Test
  public void testFindById() {
    Long id = 1L;

    doReturn(Optional.of(input)).when(requestRepository).findById(id);

    assertEquals(outputRequestDTO, requestService.findById(id));
  }

  @Test
  public void testGetAll() {
    doReturn(list).when(requestRepository).findAll();

    assertEquals(listDTO, requestService.getAll());
  }

  @Test
  public void testGetAllByEmployeeId() {
    Long id = 1L;

    doReturn(list).when(requestRepository).getAllByEmployeeId(id);

    assertEquals(listDTO, requestService.getAllByEmployeeId(id));
  }

  @Test
  public void testGetAllByAdminId() {
    Long id = 1L;

    doReturn(list).when(requestRepository).getAllByAdminId(id);

    assertEquals(listDTO, requestService.getAllByAdminId(id));
  }

  @Test
  public void testDeleteById() {
    Long id = 1L;

    doNothing().when(requestRepository).deleteById(id);

    requestService.deleteById(id);

    verify(requestRepository).deleteById(id);
  }
}
