package by.netcracker.enterprisedb.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(
    name = "employee",
    uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Employee extends BaseEntity {

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;

  private int amountOfHolidays;

  private int insuranceNumber;

  @Email
  private String email;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @NotBlank
  private String password;

  @OneToMany(mappedBy = "employee")
  private Set<Bonus> bonuses = new HashSet<>();

  @OneToMany(mappedBy = "employee")
  private Set<Career> careers = new HashSet<>();

  @OneToMany(mappedBy = "employee")
  private Set<Holiday> holidays = new HashSet<>();

  @OneToMany(mappedBy = "admin")
  private Set<News> news = new HashSet<>();

  @OneToMany(mappedBy = "employee")
  private Set<Request> sentRequests = new HashSet<>();

  @OneToMany(mappedBy = "admin")
  private Set<Request> receivedRequests = new HashSet<>();
}
