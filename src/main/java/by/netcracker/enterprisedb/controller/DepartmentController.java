package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.DepartmentDTO;
import by.netcracker.enterprisedb.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

  private final DepartmentService departmentService;

  @Autowired
  public DepartmentController(final DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final DepartmentDTO departmentDTO) {
    return ResponseEntity.ok(departmentService.save(departmentDTO));
  }

  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final DepartmentDTO departmentDTO) {
    if(departmentDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(departmentService.update(departmentDTO));
  }

  @GetMapping("/all/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(departmentService.findById(id));
  }

  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(departmentService.getAll());
  }

  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    departmentService.deleteById(id);
    return ResponseEntity.ok("Department deleted");
  }
}
