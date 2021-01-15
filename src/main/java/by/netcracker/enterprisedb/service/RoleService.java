package by.netcracker.enterprisedb.service;

import by.netcracker.enterprisedb.dao.entity.ERole;
import by.netcracker.enterprisedb.dao.entity.Role;

import java.util.Optional;

public interface RoleService {

  boolean existsByName(final ERole name);

  Optional<Role> findByName(final ERole name);
}
