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
public class HolidayDTO extends BaseDTO {

  @ApiModelProperty(position = 1, hidden = true)
  private EmployeeDTO employee;

  @ApiModelProperty(position = 2)
  @Positive(message = "Employee ID must be positive")
  private Long empId;

  @ApiModelProperty(position = 3)
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate startDate;

  @ApiModelProperty(position = 4)
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate endDate;
}
