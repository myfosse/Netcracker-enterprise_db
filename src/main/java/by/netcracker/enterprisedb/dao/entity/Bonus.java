package by.netcracker.enterprisedb.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bonus extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "emp_id")
  private Employee employee;

  @Column(insertable = false, updatable = false)
  private Long emp_id;

  private int month;

  private int year;

  private int value;
}
