package by.netcracker.enterprisedb.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Position extends BaseEntity {

  private String specialty;

  private int minSalary;

  @OneToMany(mappedBy = "position")
  private Set<Career> careers = new HashSet<>();

  public Position(String specialty, int minSalary) {
    this.specialty = specialty;
    this.minSalary = minSalary;
  }
}
