package by.netcracker.enterprisedb.dto.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO extends BaseDTO {

  @NotBlank
  private String specialty;

  private int minSalary;
}
