package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.converter.request.SignUpRequestToUserConverter;
import by.netcracker.enterprisedb.dao.entity.ERole;
import by.netcracker.enterprisedb.dao.entity.Role;
import by.netcracker.enterprisedb.payload.request.EmployeeUpdateRequest;
import by.netcracker.enterprisedb.payload.request.SignUpRequest;
import by.netcracker.enterprisedb.service.EmployeeService;
import by.netcracker.enterprisedb.service.RoleService;
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

  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(
      @Valid @RequestBody final SignUpRequest signUpRequest) {

    if (employeeService.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(("Error: Email is already in use!"));
    }

    if (!signUpRequest.getPassword().equals(signUpRequest.getPasswordConfirmation())) {
      return ResponseEntity.badRequest().body("Error: Passwords doesn't match!");
    }

    if (signUpRequest.getPassword().equals(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body("Error: Password must not match email!");
    }

    Role userRole =
        roleService
            .findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

    return ResponseEntity.ok(
        employeeService.save(
            SignUpRequestToUserConverter.convertSignUpRequestToUser(
                signUpRequest, userRole, encoder)));
  }

  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(
      @Valid @RequestBody final EmployeeUpdateRequest employeeUpdateRequest) {

    if (employeeService.existsByEmail(employeeUpdateRequest.getEmail())) {
      return ResponseEntity.badRequest().body(("Error: Email is already in use!"));
    }

    if (!employeeUpdateRequest
        .getPassword()
        .equals(employeeUpdateRequest.getPasswordConfirmation())) {
      return ResponseEntity.badRequest().body("Error: Passwords doesn't match!");
    }

    if (employeeUpdateRequest.getPassword().equals(employeeUpdateRequest.getEmail())) {
      return ResponseEntity.badRequest().body("Error: Password must not match email!");
    }

    Set<Role> roles = new HashSet<>();
    Role role = new Role(1L, ERole.ROLE_USER);
    for (ERole eRole : employeeUpdateRequest.getRoles()) {
      roles.add(roleService.findByName(eRole).orElse(role));
    }

    return ResponseEntity.ok(
        employeeService.save(
            SignUpRequestToUserConverter.convertUpdateUserRequestToUser(
                employeeUpdateRequest, roles, encoder)));
  }

  @GetMapping("/user/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(employeeService.findById(id));
  }

  @GetMapping("/user/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(employeeService.getAll());
  }

  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    employeeService.deleteById(id);
    return ResponseEntity.ok("Employee deleted");
  }
}
