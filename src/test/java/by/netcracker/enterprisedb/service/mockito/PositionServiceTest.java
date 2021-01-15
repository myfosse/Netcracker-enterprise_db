package by.netcracker.enterprisedb.service.mockito;

import by.netcracker.enterprisedb.dao.entity.Position;
import by.netcracker.enterprisedb.dao.repository.PositionRepository;
import by.netcracker.enterprisedb.dto.model.PositionDTO;
import by.netcracker.enterprisedb.service.PositionService;
import by.netcracker.enterprisedb.service.impl.PositionServiceImpl;
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
@ContextConfiguration(classes = {PositionServiceImpl.class})
public class PositionServiceTest extends BaseServiceTest<Position, PositionDTO> {

  public PositionServiceTest() throws IOException {
    list = Arrays.asList(mapper.readValue(getAbsoluteFile("position"), Position[].class));
    listDTO = Arrays.asList(mapper.readValue(getAbsoluteFile("positionDTO"), PositionDTO[].class));
    input = list.get(0);
    inputDTO = listDTO.get(0);
  }

  private final PositionDTO outputPositionDTO = new PositionDTO("Java", 1500);

  @Autowired private PositionService positionService;

  @MockBean private PositionRepository positionRepository;

  @Test
  public void testSave() {
    when(positionRepository.save(input)).thenReturn(input);

    assertEquals(outputPositionDTO, positionService.save(inputDTO));
  }

  @Test
  public void testUpdate() {
    when(positionRepository.save(input)).thenReturn(input);

    assertEquals(outputPositionDTO, positionService.update(inputDTO));
  }

  @Test
  public void testFindById() {
    Long id = 1L;

    doReturn(Optional.of(input)).when(positionRepository).findById(id);

    assertEquals(new PositionDTO("Java", 1500), positionService.findById(id));
  }

  @Test
  public void testGetAll() {
    doReturn(list).when(positionRepository).findAll();

    assertEquals(listDTO, positionService.getAll());
  }

  @Test
  public void testDeleteById() {
    Long id = 1L;

    doNothing().when(positionRepository).deleteById(id);

    positionService.deleteById(id);

    verify(positionRepository).deleteById(id);
  }
}
