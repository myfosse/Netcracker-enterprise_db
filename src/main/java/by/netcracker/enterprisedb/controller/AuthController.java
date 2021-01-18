package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dao.entity.ERole;
import by.netcracker.enterprisedb.dao.entity.Role;
import by.netcracker.enterprisedb.dto.model.EmployeeDTO;
import by.netcracker.enterprisedb.service.EmployeeService;
import by.netcracker.enterprisedb.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/** @author Andrey Egorov */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final EmployeeService employeeService;
  private final RoleService roleService;
  private final PasswordEncoder encoder;

  @Autowired
  public AuthController(
      EmployeeService employeeService, RoleService roleService, PasswordEncoder encoder) {
    this.employeeService = employeeService;
    this.roleService = roleService;
    this.encoder = encoder;
  }

  @ApiOperation(value = "Sign up", notes = "This method allows anybody register on site")
  @PostMapping("/sign-up")
  public @ResponseBody ResponseEntity<?> registerUser(
      @Valid @RequestBody final EmployeeDTO employeeDTO) {

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
}
