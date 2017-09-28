package complaint.dao.user;

import complaint.model.user.Employee;

public interface EmployeeDao {

    void persist(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);

    Employee findById(long id);
}
