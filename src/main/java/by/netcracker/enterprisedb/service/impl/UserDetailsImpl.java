package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.dao.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** @author Andrey Egorov */
@Getter
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private final Long id;
  private final String name;
  private final String surname;
  private final LocalDate birthdate;
  private final int amountOfHolidays;
  private final int insuranceNumber;
  private final String email;
  @JsonIgnore private final String password;

  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
      Long id,
      String name,
      String surname,
      LocalDate birthdate,
      int amountOfHolidays,
      int insuranceNumber,
      String email,
      String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.birthdate = birthdate;
    this.amountOfHolidays = amountOfHolidays;
    this.insuranceNumber = insuranceNumber;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(Employee user) {
    List<GrantedAuthority> authorities =
        user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(),
        user.getName(),
        user.getSurname(),
        user.getBirthdate(),
        user.getAmountOfHolidays(),
        user.getInsuranceNumber(),
        user.getEmail(),
        user.getPassword(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getUsername() {
    return surname;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
