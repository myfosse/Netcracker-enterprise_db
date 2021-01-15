package by.netcracker.enterprisedb.service.impl;

import by.netcracker.enterprisedb.dao.entity.ERole;
import by.netcracker.enterprisedb.dao.entity.Role;
import by.netcracker.enterprisedb.dao.repository.RoleRepository;
import by.netcracker.enterprisedb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Autowired
  public RoleServiceImpl(final RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public boolean existsByName(final ERole name) {
    return roleRepository.existsByName(name);
  }

  @Override
  public Optional<Role> findByName(final ERole name) {
    return roleRepository.findByName(name);
  }
}
