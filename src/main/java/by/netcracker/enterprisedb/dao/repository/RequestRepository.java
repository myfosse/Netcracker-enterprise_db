package by.netcracker.enterprisedb.dao.repository;

import by.netcracker.enterprisedb.dao.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

  List<Request> getAllByEmployeeId(final Long employeeId);

  List<Request> getAllByAdminId(final Long adminId);
}
