package by.netcracker.enterprisedb.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "emp_id")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "adm_id")
  private Employee admin;

  @NotBlank
  private String title;

  @NotBlank
  private String text;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate sendDate;

  private boolean agreed;
}
