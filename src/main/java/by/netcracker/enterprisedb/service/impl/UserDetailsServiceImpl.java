package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.dao.entity.Employee;
import by.netcracker.enterprisedb.dao.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Andrey Egorov */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public UserDetailsServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Employee user =
        employeeRepository
            .findByEmail(email)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with email: " + email));

    return UserDetailsImpl.build(user);
  }
}
