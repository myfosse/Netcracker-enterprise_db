package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(final EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PutMapping("/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final EmployeeDTO employeeDTO) {
    return ResponseEntity.ok(employeeService.update(employeeDTO));
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(employeeService.findById(id));
  }

  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(employeeService.getAll());
  }

  @DeleteMapping("/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    employeeService.deleteById(id);
    return ResponseEntity.ok("Employee deleted");
  }
}
