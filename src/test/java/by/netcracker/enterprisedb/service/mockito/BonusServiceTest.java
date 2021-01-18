package by.netcracker.enterprisedb.service.mockito;

import by.netcracker.enterprisedb.dao.entity.Bonus;
import by.netcracker.enterprisedb.dao.repository.BonusRepository;
import by.netcracker.enterprisedb.dto.model.BonusDTO;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.service.BonusService;
import by.netcracker.enterprisedb.service.impl.BonusServiceImpl;
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
@ContextConfiguration(classes = {BonusServiceImpl.class})
public class BonusServiceTest extends BaseServiceTest<Bonus, BonusDTO> {

  public BonusServiceTest() throws IOException {
    list = Arrays.asList(mapper.readValue(getAbsoluteFile("bonus"), Bonus[].class));
    listDTO = Arrays.asList(mapper.readValue(getAbsoluteFile("bonusDTO"), BonusDTO[].class));
    input = list.get(0);
    inputDTO = listDTO.get(0);
  }

  private final BonusDTO outputBonusDTO =
      new BonusDTO(
          new EmployeeDTO("String", "String", "String@mail.ru", null, 1, 1, "String", null, null),
          1L,
          1,
          1,
          1);

  @Autowired private BonusService bonusService;

  @MockBean private BonusRepository bonusRepository;

  @Test
  public void testSave() {
    when(bonusRepository.save(input)).thenReturn(input);

    assertEquals(outputBonusDTO, bonusService.save(inputDTO));
  }

  @Test
  public void testUpdate() {
    when(bonusRepository.save(input)).thenReturn(input);

    assertEquals(outputBonusDTO, bonusService.update(inputDTO));
  }

  @Test
  public void testFindById() {
    Long id = 1L;

    doReturn(Optional.of(input)).when(bonusRepository).findById(id);

    assertEquals(outputBonusDTO, bonusService.findById(id));
  }

  @Test
  public void testGetAll() {
    doReturn(list).when(bonusRepository).findAll();

    assertEquals(listDTO, bonusService.getAll());
  }

  @Test
  public void testGetAllByEmployeeId() {
    Long id = 1L;

    doReturn(list).when(bonusRepository).getAllByEmployeeId(id);

    assertEquals(listDTO, bonusService.getAllByEmployeeId(id));
  }

  @Test
  public void testDeleteById() {
    Long id = 1L;

    doNothing().when(bonusRepository).deleteById(id);

    bonusService.deleteById(id);

    verify(bonusRepository).deleteById(id);
  }
}
