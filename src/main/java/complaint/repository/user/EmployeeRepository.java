package complaint.repository.user;

import complaint.model.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmployeeId(long employeeId);

    @Query("select e from Employee where e.user.userId = :userId")
    Optional<Employee> findByUserId(@Param("userId") long userId);
}
