package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.HolidayDTO;
import by.netcracker.enterprisedb.payload.response.MessageResponse;
import by.netcracker.enterprisedb.service.HolidayService;
import by.netcracker.enterprisedb.service.impl.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

  @ApiOperation(
      value = "Add holiday",
      notes = "This method allows admin add new holiday for employee")
  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final HolidayDTO holidayDTO) {
    return ResponseEntity.ok(holidayService.save(holidayDTO));
  }

  @ApiOperation(
      value = "Update holiday",
      notes = "This method allows admin update holiday for employee")
  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final HolidayDTO holidayDTO) {
    if (holidayDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(holidayService.update(holidayDTO));
  }

  @ApiOperation(value = "Get holiday", notes = "This method allows admin or user get holiday by ID")
  @GetMapping("/user/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(holidayService.findById(id));
  }

  @ApiOperation(
      value = "Get all holidays",
      notes = "This method allows admin or user get all holidays")
  @GetMapping("/user/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(holidayService.getAll());
  }

  @ApiOperation(
      value = "Get all holidays by employee ID",
      notes = "This method allows admin or user get all holidays by employee ID")
  @GetMapping("/user/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByAdminId(
      @PathVariable("employeeId") final Long employeeId) {
    return ResponseEntity.ok(holidayService.getAllByEmployeeId(employeeId));
  }

  @ApiOperation(
      value = "Get all finished holidays",
      notes = "This method allows admin or user get all finished holidays")
  @GetMapping("/user/finished")
  public @ResponseBody ResponseEntity<?> getAllFinishedHolidays(
      @Param(value = "finished") final boolean finished) {
    return ResponseEntity.ok(
        finished
            ? holidayService.getAllFinishedHolidays()
            : holidayService.getAllNotFinishedHolidays());
  }

  @ApiOperation(value = "Delete holidays", notes = "This method allows admin delete holiday by ID")
  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    holidayService.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("Holiday deleted"));
  }
}
