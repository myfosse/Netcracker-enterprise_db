package by.netcracker.enterprisedb.converter.request;

import by.netcracker.enterprisedb.dao.entity.Employee;
import by.netcracker.enterprisedb.dao.entity.Role;
import by.netcracker.enterprisedb.payload.request.EmployeeUpdateRequest;
import by.netcracker.enterprisedb.payload.request.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/** @author Andrey Egorov */
public class SignUpRequestToUserConverter {

  public static Employee convertSignUpRequestToUser(
      final SignUpRequest signUpRequest, final Role role, final PasswordEncoder encoder) {
    return Employee.builder()
        .name(signUpRequest.getName())
        .surname(signUpRequest.getSurname())
        .birthdate(signUpRequest.getBirthdate())
        .amountOfHolidays(0)
        .insuranceNumber(signUpRequest.getInsuranceNumber())
        .email(signUpRequest.getEmail())
        .roles(Set.of(role))
        .password(encoder.encode(signUpRequest.getPassword()))
        .build();
  }

  public static Employee convertUpdateUserRequestToUser(
      final EmployeeUpdateRequest employeeUpdateRequest,
      final Set<Role> roles,
      final PasswordEncoder encoder) {
    return Employee.builder()
        .name(employeeUpdateRequest.getName())
        .surname(employeeUpdateRequest.getSurname())
        .birthdate(employeeUpdateRequest.getBirthdate())
        .amountOfHolidays(employeeUpdateRequest.getAmountOfHolidays())
        .insuranceNumber(employeeUpdateRequest.getInsuranceNumber())
        .email(employeeUpdateRequest.getEmail())
        .roles(roles)
        .password(encoder.encode(employeeUpdateRequest.getPassword()))
        .build();
  }
}
