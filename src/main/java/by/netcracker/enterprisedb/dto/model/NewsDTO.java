package by.netcracker.enterprisedb.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO extends BaseDTO {

  @NotNull
  private EmployeeDTO admin;

  @NotBlank
  private String title;

  @NotBlank
  private String text;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate postDate;
}
