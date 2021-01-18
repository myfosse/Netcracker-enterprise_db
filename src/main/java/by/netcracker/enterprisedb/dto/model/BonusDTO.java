package by.netcracker.enterprisedb.dto.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BonusDTO extends BaseDTO {

  @ApiModelProperty(position = 1, hidden = true)
  private EmployeeDTO employee;

  @ApiModelProperty(position = 2)
  @Positive(message = "Employee ID must be positive")
  private Long emp_id;

  @ApiModelProperty(position = 3)
  @Min(value = 1, message = "The month must be between 1 and 12")
  @Max(value = 12, message = "The month must be between 1 and 12")
  private int month;

  @ApiModelProperty(position = 4)
  @Min(value = 1900, message = "The year must be between 1900 and 3000")
  @Max(value = 3000, message = "The year must be between 1900 and 3000")
  private int year;

  @ApiModelProperty(position = 5)
  @Positive(message = "Value bonus must be positive")
  private int value;
}
