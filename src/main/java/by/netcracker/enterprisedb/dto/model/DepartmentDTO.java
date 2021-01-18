package by.netcracker.enterprisedb.dto.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO extends BaseDTO {

  @ApiModelProperty(position = 1)
  @NotBlank
  private String name;

  @ApiModelProperty(position = 2)
  @NotBlank
  @Pattern(
      regexp = "^[A-Z][a-zA-Z-']{1,40}$",
      message = "Country should have 2-40 characters, the first character must be a letter")
  private String country;

  @ApiModelProperty(position = 3)
  @NotBlank
  @Pattern(
      regexp = "^[A-Z][a-zA-Z-']{1,40}$",
      message = "City should have 2-40 characters, the first character must be a letter")
  private String city;
}
