package by.netcracker.enterprisedb.dao.repository;

import by.netcracker.enterprisedb.dao.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail(final String email);

    Optional<Employee> findByEmail(final String email);
}
