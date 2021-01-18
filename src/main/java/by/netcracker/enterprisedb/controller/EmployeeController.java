package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dao.entity.ERole;
import by.netcracker.enterprisedb.dao.entity.Role;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.payload.response.MessageResponse;
import by.netcracker.enterprisedb.service.EmployeeService;
import by.netcracker.enterprisedb.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

  private final EmployeeService employeeService;
  private final RoleService roleService;
  private final PasswordEncoder encoder;

  @Autowired
  public EmployeeController(
      final EmployeeService employeeService, RoleService roleService, PasswordEncoder encoder) {
    this.employeeService = employeeService;
    this.roleService = roleService;
    this.encoder = encoder;
  }

  @ApiOperation(value = "Add new employee", notes = "This method allows admin add new employee")
  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody EmployeeDTO employeeDTO) {

    if (employeeService.existsByEmail(employeeDTO.getEmail())) {
      return ResponseEntity.badRequest().body(("Error: Email is already in use!"));
    }

    if (!employeeDTO.getPassword().equals(employeeDTO.getPasswordConfirmation())) {
      return ResponseEntity.badRequest().body("Error: Passwords doesn't match!");
    }

    if (employeeDTO.getPassword().equals(employeeDTO.getEmail())) {
      return ResponseEntity.badRequest().body("Error: Password must not match email!");
    }

    Role userRole =
        roleService
            .findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

    employeeDTO.setRoles(Set.of(userRole));
    employeeDTO.setPassword(encoder.encode(employeeDTO.getPassword()));

    return ResponseEntity.ok(employeeService.save(employeeDTO));
  }

  @ApiOperation(value = "Update employee", notes = "This method allows admin update employee")
  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final EmployeeDTO employeeDTO) {

    if (employeeService.existsByEmail(employeeDTO.getEmail())) {
      return ResponseEntity.badRequest().body(("Error: Email is already in use!"));
    }

    if (!employeeDTO.getPassword().equals(employeeDTO.getPasswordConfirmation())) {
      return ResponseEntity.badRequest().body("Error: Passwords doesn't match!");
    }

    if (employeeDTO.getPassword().equals(employeeDTO.getEmail())) {
      return ResponseEntity.badRequest().body("Error: Password must not match email!");
    }

    Set<Role> roles = new HashSet<>();
    Role role = new Role(1L, ERole.ROLE_USER);
    for (Role role1 : employeeDTO.getRoles()) {
      roles.add(roleService.findByName(role1.getName()).orElse(role));
    }

    employeeDTO.setRoles(roles);
    employeeDTO.setPassword(encoder.encode(employeeDTO.getPassword()));

    return ResponseEntity.ok(employeeService.save(employeeDTO));
  }

  @ApiOperation(value = "Get employee", notes = "This method allows admin or user get employee")
  @GetMapping("/user/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(employeeService.findById(id));
  }

  @ApiOperation(
      value = "Get all employees",
      notes = "This method allows admin or user get employees")
  @GetMapping("/user/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(employeeService.getAll());
  }

  @ApiOperation(
      value = "Delete employee",
      notes = "This method allows admin delete employee by ID")
  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    employeeService.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("Employee deleted"));
  }
}
