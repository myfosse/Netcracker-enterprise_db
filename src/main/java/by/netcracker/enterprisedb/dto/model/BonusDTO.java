package by.netcracker.enterprisedb.dto.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BonusDTO extends BaseDTO {

  @NotNull
  private EmployeeDTO employee;

  private int month;

  private int year;

  private int value;
}
