package by.netcracker.enterprisedb.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerDTO extends BaseDTO {

  @ApiModelProperty(position = 1, hidden = true)
  private EmployeeDTO employee;

  @ApiModelProperty(position = 2, hidden = true)
  private DepartmentDTO department;

  @ApiModelProperty(position = 3, hidden = true)
  private PositionDTO position;

  @ApiModelProperty(position = 4)
  @Positive(message = "Employee ID must be positive")
  private Long empId;

  @ApiModelProperty(position = 5)
  @Positive(message = "Department ID must be positive")
  private Long deptId;

  @ApiModelProperty(position = 6)
  @Positive(message = "Position ID must be positive")
  private Long posId;

  @ApiModelProperty(position = 7)
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate startDate;

  @ApiModelProperty(position = 8)
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate endDate;
}
