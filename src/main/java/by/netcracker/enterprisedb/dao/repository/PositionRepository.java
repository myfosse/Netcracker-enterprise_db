package by.netcracker.enterprisedb.dao.repository;

import by.netcracker.enterprisedb.dao.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {}
