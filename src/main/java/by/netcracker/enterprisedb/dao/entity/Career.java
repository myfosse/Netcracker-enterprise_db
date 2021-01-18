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
public class Career extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "emp_id")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "dept_id")
  private Department department;

  @ManyToOne
  @JoinColumn(name = "pos_id")
  private Position position;

  @Column(insertable = false, updatable = false)
  private Long emp_id;

  @Column(insertable = false, updatable = false)
  private Long dept_id;

  @Column(insertable = false, updatable = false)
  private Long pos_id;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
}
