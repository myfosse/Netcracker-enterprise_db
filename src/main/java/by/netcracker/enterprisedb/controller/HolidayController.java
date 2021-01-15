package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.HolidayDTO;
import by.netcracker.enterprisedb.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/holiday")
public class HolidayController {

  private final HolidayService holidayService;

  @Autowired
  public HolidayController(final HolidayService holidayService) {
    this.holidayService = holidayService;
  }

  @PostMapping("/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final HolidayDTO holidayDTO) {
    return ResponseEntity.ok(holidayService.save(holidayDTO));
  }

  @PutMapping("/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final HolidayDTO holidayDTO) {
    return ResponseEntity.ok(holidayService.update(holidayDTO));
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(holidayService.findById(id));
  }

  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(holidayService.getAll());
  }

  @GetMapping("/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByAdminId(
      @PathVariable("employeeId") final Long adminId) {
    return ResponseEntity.ok(holidayService.getAllByEmployeeId(adminId));
  }

  @GetMapping("/all/finished")
  public @ResponseBody ResponseEntity<?> getAllFinishedHolidays(
      @Param(value = "finished") final boolean finished) {
    return ResponseEntity.ok(
        finished
            ? holidayService.getAllFinishedHolidays()
            : holidayService.getAllNotFinishedHolidays());
  }

  @DeleteMapping("/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    holidayService.deleteById(id);
    return ResponseEntity.ok("Holiday deleted");
  }
}
