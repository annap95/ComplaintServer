<<<<<<< HEAD
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
=======
package complaint.repository.user;

import complaint.model.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmployeeId(long employeeId);

    @Query("select e from Employee e where e.user.userId = :userId")
    Optional<Employee> findByUserId(@Param("userId") long userId);

    List<Employee> findAll();

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
