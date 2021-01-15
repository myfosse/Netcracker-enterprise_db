package by.netcracker.enterprisedb.dao.repository;

import by.netcracker.enterprisedb.dao.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

  List<Bonus> getAllByEmployeeId(final Long employeeId);
}
