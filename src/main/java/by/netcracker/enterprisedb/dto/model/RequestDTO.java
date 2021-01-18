package by.netcracker.enterprisedb.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO extends BaseDTO {

  @ApiModelProperty(position = 1, hidden = true)
  private EmployeeDTO employee;

  @ApiModelProperty(position = 2, hidden = true)
  private EmployeeDTO admin;

  @ApiModelProperty(position = 3)
  @Positive(message = "Employee ID must be positive")
  private Long empId;

  @ApiModelProperty(position = 4)
  @Positive(message = "Admin ID must be positive")
  private Long admId;

  @ApiModelProperty(position = 5)
  @NotBlank
  private String title;

  @ApiModelProperty(position = 6)
  @NotBlank
  private String text;

  @ApiModelProperty(position = 7, hidden = true)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate sendDate;
}
