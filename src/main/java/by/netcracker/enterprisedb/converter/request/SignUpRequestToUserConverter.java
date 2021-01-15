package by.netcracker.enterprisedb.converter.request;

import by.netcracker.enterprisedb.dao.entity.Employee;
import by.netcracker.enterprisedb.dao.entity.Role;
import by.netcracker.enterprisedb.request.SignupRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/** @author Andrey Egorov */
public class SignUpRequestToUserConverter {

  public static Employee convertSignUpRequestToUser(
      final SignupRequest signUpRequest, final Role role, final PasswordEncoder encoder) {
    return Employee.builder()
            .name(signUpRequest.getName())
            .surname(signUpRequest.getSurname())
            .birthdate(signUpRequest.getBirthdate())
            .amountOfHolidays(signUpRequest.getAmountOfHolidays())
            .insuranceNumber(signUpRequest.getInsuranceNumber())
            .email(signUpRequest.getEmail())
            .roles(Set.of(role))
            .password(encoder.encode(signUpRequest.getPassword()))
            .build();
  }
}
