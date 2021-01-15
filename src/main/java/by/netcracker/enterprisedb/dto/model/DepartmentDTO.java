package by.netcracker.enterprisedb.dto.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO extends BaseDTO {

  @NotBlank
  private String name;

  @NotBlank
  private String country;

  @NotBlank
  private String city;
}
