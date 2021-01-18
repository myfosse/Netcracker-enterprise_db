package by.netcracker.enterprisedb.dto.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO extends BaseDTO {

  @ApiModelProperty(position = 1)
  @NotBlank
  private String specialty;

  @ApiModelProperty(position = 2)
  @Positive(message = "Minimum salary must be positive")
  private int minSalary;
}
