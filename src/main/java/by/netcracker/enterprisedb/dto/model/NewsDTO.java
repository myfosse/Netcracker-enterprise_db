package by.netcracker.enterprisedb.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO extends BaseDTO {

  @ApiModelProperty(position = 1, hidden = true)
  private EmployeeDTO admin;

  @ApiModelProperty(position = 2)
  @Positive(message = "Admin ID must be positive")
  private Long adm_id;

  @NotBlank
  @ApiModelProperty(position = 3)
  private String title;

  @NotBlank
  @ApiModelProperty(position = 4)
  private String text;


  @JsonFormat(pattern = "yyyy-MM-dd")
  @ApiModelProperty(position = 5, hidden = true)
  private LocalDate postDate;
}
