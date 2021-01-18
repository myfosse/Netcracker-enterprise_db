package by.netcracker.enterprisedb.dto.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {

  @ApiModelProperty(hidden = true)
  private Long id;
}
