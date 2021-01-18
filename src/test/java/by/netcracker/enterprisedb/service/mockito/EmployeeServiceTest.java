package by.netcracker.enterprisedb.service.mockito;

import by.netcracker.enterprisedb.dao.entity.Employee;
import by.netcracker.enterprisedb.dao.repository.EmployeeRepository;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.service.EmployeeService;
import by.netcracker.enterprisedb.service.impl.EmployeeServiceImpl;
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
@ContextConfiguration(classes = {EmployeeServiceImpl.class})
public class EmployeeServiceTest extends BaseServiceTest<Employee, EmployeeDTO> {

  public EmployeeServiceTest() throws IOException {
    list = Arrays.asList(mapper.readValue(getAbsoluteFile("employee"), Employee[].class));
    listDTO = Arrays.asList(mapper.readValue(getAbsoluteFile("employeeDTO"), EmployeeDTO[].class));
    input = list.get(0);
    inputDTO = listDTO.get(0);
  }

  private final EmployeeDTO outputEmployeeDTO =
      new EmployeeDTO("String", "String", "String@mail.ru", null, 1, 1, "String", null, null);

  @Autowired private EmployeeService employeeService;

  @MockBean private EmployeeRepository employeeRepository;

  @Test
  public void testSave() {
    when(employeeRepository.save(input)).thenReturn(input);

    assertEquals(outputEmployeeDTO, employeeService.save(inputDTO));
  }

  @Test
  public void testUpdate() {
    when(employeeRepository.save(input)).thenReturn(input);

    assertEquals(outputEmployeeDTO, employeeService.update(inputDTO));
  }

  @Test
  public void testFindById() {
    Long id = 1L;

    doReturn(Optional.of(input)).when(employeeRepository).findById(id);

    assertEquals(outputEmployeeDTO, employeeService.findById(id));
  }

  @Test
  public void testGetAll() {
    doReturn(list).when(employeeRepository).findAll();

    assertEquals(listDTO, employeeService.getAll());
  }

  @Test
  public void testDeleteById() {
    Long id = 1L;

    doNothing().when(employeeRepository).deleteById(id);

    employeeService.deleteById(id);

    verify(employeeRepository).deleteById(id);
  }
}
