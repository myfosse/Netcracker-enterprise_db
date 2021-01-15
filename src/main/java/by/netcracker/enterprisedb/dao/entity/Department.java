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
public class Department extends BaseEntity {

  private String name;

  private String country;

  private String city;

  @OneToMany(mappedBy = "department")
  private Set<Career> careers = new HashSet<>();

  public Department(String name, String country, String city) {
    this.name = name;
    this.country = country;
    this.city = city;
  }
}
