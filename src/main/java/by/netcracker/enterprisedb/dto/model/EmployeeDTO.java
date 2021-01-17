package by.netcracker.enterprisedb.dto.model;

import by.netcracker.enterprisedb.dao.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO extends BaseDTO {

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate birthdate;

  private int amountOfHolidays;

  private int insuranceNumber;

  @Email
  private String email;

  @NotBlank
  @NotNull
  private Set<Role> roles;
}
