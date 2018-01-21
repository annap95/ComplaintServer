package complaint.repository.user;

import complaint.controller.user.request.EmployeeItemRequest;
import complaint.model.user.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmployeeId(long employeeId);

    @Query("select e from Employee e where e.user.userId = :userId")
    Optional<Employee> findByUserId(@Param("userId") long userId);

    @Query("select e from Employee e where " +
            "e.employeeId like concat_ws('%', '%', :#{#employee.employeeId}, '%') " +
            "and upper(e.name) like concat_ws('%', '%', upper(:#{#employee.name}), '%') " +
            "and upper(e.surname) like concat_ws('%', '%', upper(:#{#employee.surname}), '%') " +
            "and upper(e.name) like concat_ws('%', '%', upper(:#{#employee.name}), '%') " +
            "and upper(e.user.email) like concat_ws('%', '%', upper(:#{#employee.email}), '%') " +
            "and upper(e.user.userRole) like concat_ws('%', '%', upper(:#{#employee.userRole}), '%') " +
            "and (:#{#employee.enabled} is null or (e.user.enabled = :#{#employee.enabled}))")
    Page<Employee> findAll(Pageable pageable, @Param("employee")EmployeeItemRequest employee);

}
