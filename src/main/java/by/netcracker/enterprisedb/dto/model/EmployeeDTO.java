package by.netcracker.enterprisedb.dto.model;

import by.netcracker.enterprisedb.dao.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO extends BaseDTO {

  @ApiModelProperty(position = 1)
  @NotBlank
  private String name;

  @ApiModelProperty(position = 2)
  @NotBlank
  private String surname;

  @ApiModelProperty(position = 3)
  @Email(message = "Email should be valid")
  private String email;

  @ApiModelProperty(position = 4)
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  @Past
  private LocalDate birthdate;

  @ApiModelProperty(position = 5)
  @PositiveOrZero(message = "Insurance number must be greater than or equal to 0")
  private int insuranceNumber;

  @ApiModelProperty(position = 6, hidden = true)
  @PositiveOrZero(message = "Amount of holidays must be greater than or equal to 0")
  private int amountOfHolidays;

  @ApiModelProperty(position = 7)
  @Pattern(
      regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$",
      message = "Password must contain lowercase and uppercase latin letters, numbers")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @ApiModelProperty(position = 8)
  @Pattern(
      regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$",
      message = "Password must contain lowercase and uppercase latin letters, numbers")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String passwordConfirmation;

  @ApiModelProperty(position = 9, hidden = true)
  @NotBlank
  @NotNull
  private Set<Role> roles;
}
