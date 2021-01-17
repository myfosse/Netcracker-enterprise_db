package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.converter.request.SignUpRequestToUserConverter;
import by.netcracker.enterprisedb.dao.entity.ERole;
import by.netcracker.enterprisedb.dao.entity.Role;
import by.netcracker.enterprisedb.payload.request.SignUpRequest;
import by.netcracker.enterprisedb.service.EmployeeService;
import by.netcracker.enterprisedb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

  @PostMapping("/sign-up")
  public @ResponseBody ResponseEntity<?> registerUser(
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

    employeeService.save(
        SignUpRequestToUserConverter.convertSignUpRequestToUser(signUpRequest, userRole, encoder));

    return ResponseEntity.ok("Created");
  }
}
