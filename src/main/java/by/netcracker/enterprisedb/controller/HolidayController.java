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

  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final HolidayDTO holidayDTO) {
    return ResponseEntity.ok(holidayService.save(holidayDTO));
  }

  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final HolidayDTO holidayDTO) {
    if(holidayDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(holidayService.update(holidayDTO));
  }

  @GetMapping("/user/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(holidayService.findById(id));
  }

  @GetMapping("/user/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(holidayService.getAll());
  }

  @GetMapping("/user/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByAdminId(
      @PathVariable("employeeId") final Long adminId) {
    return ResponseEntity.ok(holidayService.getAllByEmployeeId(adminId));
  }

  @GetMapping("/user/finished")
  public @ResponseBody ResponseEntity<?> getAllFinishedHolidays(
      @Param(value = "finished") final boolean finished) {
    return ResponseEntity.ok(
        finished
            ? holidayService.getAllFinishedHolidays()
            : holidayService.getAllNotFinishedHolidays());
  }

  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    holidayService.deleteById(id);
    return ResponseEntity.ok("Holiday deleted");
  }
}
