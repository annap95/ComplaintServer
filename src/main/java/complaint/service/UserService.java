<<<<<<< HEAD
package complaint.service;

import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;

public interface UserService {

    void validateRegister(String email);
    void addUser(User user);

    User getUserById(long id);
    User getUserByEmail(String email);

    Customer getCustomerByUser(long userId);
    Employee getEmployeeByUser(long userId);

}
=======
package complaint.service;

import complaint.model.user.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {

    void validateRegister(String email);

    void addCustomerUser(User user, Customer customer);

    @PreAuthorize("hasAuthority('ADMIN')")
    void addEmployeeUser(User user, Employee employee);

    User getUserById(long id);

    User getUserByEmail(String email);

    Customer getCustomerByUser(long userId);

    Employee getEmployeeByUser(long userId);

    @PreAuthorize("hasAuthority('ADMIN')")
    void enableUser(User user);

    @PreAuthorize("hasAuthority('ADMIN')")
    void disableUser(User user);

    List<User> getUsers();
}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
