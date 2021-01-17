package by.netcracker.enterprisedb.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO extends BaseDTO {

  @NotNull
  private EmployeeDTO employee;

  @NotNull
  private EmployeeDTO admin;

  @NotBlank
  private String title;

  @NotBlank
  private String text;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate sendDate;

  @NotNull
  private boolean agreed;
}
