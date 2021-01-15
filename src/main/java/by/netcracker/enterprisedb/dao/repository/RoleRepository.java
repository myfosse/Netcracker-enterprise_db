package by.netcracker.enterprisedb.dao.repository;

import by.netcracker.enterprisedb.dao.entity.ERole;
import by.netcracker.enterprisedb.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  boolean existsByName(final ERole name);

  Optional<Role> findByName(final ERole name);
}
