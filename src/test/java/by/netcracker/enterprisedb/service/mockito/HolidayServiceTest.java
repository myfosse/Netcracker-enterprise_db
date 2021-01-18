package by.netcracker.enterprisedb.service.mockito;

import by.netcracker.enterprisedb.dao.entity.Holiday;
import by.netcracker.enterprisedb.dao.repository.HolidayRepository;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.dto.model.HolidayDTO;
import by.netcracker.enterprisedb.service.HolidayService;
import by.netcracker.enterprisedb.service.impl.HolidayServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/** @author Andrey Egorov */
@ContextConfiguration(classes = {HolidayServiceImpl.class})
public class HolidayServiceTest extends BaseServiceTest<Holiday, HolidayDTO> {

  public HolidayServiceTest() throws IOException {
    list = Arrays.asList(mapper.readValue(getAbsoluteFile("holiday"), Holiday[].class));
    listDTO = Arrays.asList(mapper.readValue(getAbsoluteFile("holidayDTO"), HolidayDTO[].class));
    input = list.get(0);
    inputDTO = listDTO.get(0);
  }

  private final HolidayDTO holidayDTO =
      new HolidayDTO(
          new EmployeeDTO("String", "String", "String@mail.ru", null, 1, 1, "String", null, null),
          1L,
          null,
          null);

  @Autowired private HolidayService holidayService;

  @MockBean private HolidayRepository holidayRepository;

  @Test
  public void testSave() {
    when(holidayRepository.save(input)).thenReturn(input);

    assertEquals(holidayDTO, holidayService.save(inputDTO));
  }

  @Test
  public void testUpdate() {
    when(holidayRepository.save(input)).thenReturn(input);

    assertEquals(holidayDTO, holidayService.update(inputDTO));
  }

  @Test
  public void testFindById() {
    Long id = 1L;

    doReturn(Optional.of(input)).when(holidayRepository).findById(id);

    assertEquals(holidayDTO, holidayService.findById(id));
  }

  @Test
  public void testGetAll() {

    doReturn(list).when(holidayRepository).findAll();

    assertEquals(listDTO, holidayService.getAll());
  }

  @Test
  public void testGetAllByHolidayId() {
    Long id = 1L;

    doReturn(list).when(holidayRepository).getAllByEmployeeId(id);

    assertEquals(listDTO, holidayService.getAllByEmployeeId(id));
  }

  @Test
  public void testGetAllFinishedHolidays() {
    List<Holiday> listOfHolidays = Collections.emptyList();

    doReturn(listOfHolidays).when(holidayRepository).getAllFinishedHolidays();

    assertTrue(holidayService.getAllFinishedHolidays().isEmpty());
  }

  @Test
  public void testGetAllNotFinishedHolidays() {
    doReturn(list).when(holidayRepository).getAllNotFinishedHolidays();

    assertEquals(listDTO, holidayService.getAllNotFinishedHolidays());
  }

  @Test
  public void testDeleteById() {
    Long id = 1L;

    doNothing().when(holidayRepository).deleteById(id);

    holidayService.deleteById(id);

    verify(holidayRepository).deleteById(id);
  }
}
