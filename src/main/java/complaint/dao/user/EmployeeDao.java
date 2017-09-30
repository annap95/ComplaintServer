package complaint.dao.user;

import complaint.model.user.Employee;

import java.util.Optional;

public interface EmployeeDao {

    void persist(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);

    Optional<Employee> findById(long id);
    Optional<Employee> findByUser(long userId);
}
