package by.netcracker.enterprisedb.service.mockito;

import by.netcracker.enterprisedb.dao.entity.Department;
import by.netcracker.enterprisedb.dao.repository.DepartmentRepository;
import by.netcracker.enterprisedb.dto.model.DepartmentDTO;
import by.netcracker.enterprisedb.service.DepartmentService;
import by.netcracker.enterprisedb.service.impl.DepartmentServiceImpl;
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
@ContextConfiguration(classes = {DepartmentServiceImpl.class})
public class DepartmentServiceTest extends BaseServiceTest<Department, DepartmentDTO> {

  public DepartmentServiceTest() throws IOException {
    list = Arrays.asList(mapper.readValue(getAbsoluteFile("department"), Department[].class));
    listDTO =
        Arrays.asList(mapper.readValue(getAbsoluteFile("departmentDTO"), DepartmentDTO[].class));
    input = list.get(0);
    inputDTO = listDTO.get(0);
  }

  private final DepartmentDTO outputDepartmentDTO =
      new DepartmentDTO("Department", "Belarus", "Minsk");

  @Autowired private DepartmentService departmentService;

  @MockBean private DepartmentRepository departmentRepository;

  @Test
  public void testSave() {
    when(departmentRepository.save(input)).thenReturn(input);

    assertEquals(outputDepartmentDTO, departmentService.save(inputDTO));
  }

  @Test
  public void testUpdate() {
    when(departmentRepository.save(input)).thenReturn(input);

    assertEquals(outputDepartmentDTO, departmentService.update(inputDTO));
  }

  @Test
  public void testFindById() {
    Long id = 1L;

    doReturn(Optional.of(input)).when(departmentRepository).findById(id);

    assertEquals(outputDepartmentDTO, departmentService.findById(id));
  }

  @Test
  public void testGetAll() {
    doReturn(list).when(departmentRepository).findAll();

    assertEquals(listDTO, departmentService.getAll());
  }

  @Test
  public void testDeleteById() {
    Long id = 1L;

    doNothing().when(departmentRepository).deleteById(id);

    departmentService.deleteById(id);

    verify(departmentRepository).deleteById(id);
  }
}
