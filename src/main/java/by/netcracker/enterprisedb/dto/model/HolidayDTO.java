package by.netcracker.enterprisedb.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDTO extends BaseDTO {

  @NotNull
  private EmployeeDTO employee;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate startDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate endDate;
}
