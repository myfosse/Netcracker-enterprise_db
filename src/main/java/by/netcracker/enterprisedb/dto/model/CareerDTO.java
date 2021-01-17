package by.netcracker.enterprisedb.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerDTO extends BaseDTO {

  @NotNull
  private EmployeeDTO employee;

  @NotNull
  private DepartmentDTO department;

  @NotNull
  private PositionDTO position;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate startDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate endDate;
}
