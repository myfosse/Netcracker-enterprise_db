package by.netcracker.enterprisedb.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseEntity implements GrantedAuthority {

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  @Override
  public String getAuthority() {
    return name.toString();
  }

  public Role(Long id, ERole name) {
    super(id);
    this.name = name;
  }
}
