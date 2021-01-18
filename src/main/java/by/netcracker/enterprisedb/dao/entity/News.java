package by.netcracker.enterprisedb.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "adm_id")
  private Employee admin;

  private String title;

  private String text;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate postDate;
}
