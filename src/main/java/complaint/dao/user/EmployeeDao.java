package complaint.dao.user;

import complaint.model.user.Employee;

/**
 * Created by anna on 22.09.17.
 */
public interface EmployeeDao {

    Employee find(long id);
    void persist(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);
}
