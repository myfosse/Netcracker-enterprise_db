package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.DepartmentDTO;
import by.netcracker.enterprisedb.payload.response.MessageResponse;
import by.netcracker.enterprisedb.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
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

  @ApiOperation(
      value = "Add new department",
      notes = "This method allows admin add new department")
  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(
      @Valid @RequestBody final DepartmentDTO departmentDTO) {
    return ResponseEntity.ok(departmentService.save(departmentDTO));
  }

  @ApiOperation(
      value = "Update department",
      notes = "This method allows admin to update department")
  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(
      @Valid @RequestBody final DepartmentDTO departmentDTO) {
    if (departmentDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(departmentService.update(departmentDTO));
  }

  @ApiOperation(
      value = "Get department by ID",
      notes = "This method allows everyone get department by ID")
  @GetMapping("/all/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(departmentService.findById(id));
  }

  @ApiOperation(
      value = "Get all departments",
      notes = "This method allows everyone get departments")
  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(departmentService.getAll());
  }

  @ApiOperation(
      value = "Delete department",
      notes = "This method allows admin delete department by ID")
  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    departmentService.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("Department deleted"));
  }
}
