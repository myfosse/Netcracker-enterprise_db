package by.netcracker.enterprisedb.service.mockito;

import by.netcracker.enterprisedb.dao.entity.*;
import by.netcracker.enterprisedb.dao.repository.CareerRepository;
import by.netcracker.enterprisedb.dto.model.*;
import by.netcracker.enterprisedb.service.CareerService;
import by.netcracker.enterprisedb.service.impl.CareerServiceImpl;
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
@ContextConfiguration(classes = {CareerServiceImpl.class})
public class CareerServiceTest extends BaseServiceTest<Career, CareerDTO> {

  public CareerServiceTest() throws IOException {
    list = Arrays.asList(mapper.readValue(getAbsoluteFile("career"), Career[].class));
    listDTO = Arrays.asList(mapper.readValue(getAbsoluteFile("careerDTO"), CareerDTO[].class));
    input = list.get(0);
    inputDTO = listDTO.get(0);
  }

  private final CareerDTO outputCareerDTO =
      new CareerDTO(
          new EmployeeDTO("String", "String", "String@mail.ru", null, 1, 1, "String", null, null),
          new DepartmentDTO("Department", "Belarus", "Minsk"),
          new PositionDTO("Java", 1500),
          1L,
          1L,
          1L,
          null,
          null);

  @Autowired private CareerService careerService;

  @MockBean private CareerRepository careerRepository;

  @Test
  public void testSave() {
    when(careerRepository.save(input)).thenReturn(input);

    assertEquals(outputCareerDTO, careerService.save(inputDTO));
  }

  @Test
  public void testUpdate() {
    when(careerRepository.save(input)).thenReturn(input);

    assertEquals(outputCareerDTO, careerService.update(inputDTO));
  }

  @Test
  public void testFindById() {
    Long id = 1L;

    doReturn(Optional.of(input)).when(careerRepository).findById(id);

    assertEquals(outputCareerDTO, careerService.findById(id));
  }

  @Test
  public void testGetAll() {
    doReturn(list).when(careerRepository).findAll();

    assertEquals(listDTO, careerService.getAll());
  }

  @Test
  public void testGetAllByEmployeeId() {
    Long id = 1L;

    doReturn(list).when(careerRepository).getAllByEmployeeId(id);

    assertEquals(listDTO, careerService.getAllByEmployeeId(id));
  }

  @Test
  public void testDeleteById() {
    Long id = 1L;

    doNothing().when(careerRepository).deleteById(id);

    careerService.deleteById(id);

    verify(careerRepository).deleteById(id);
  }
}
