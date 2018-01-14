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

    void validateGetCustomer(User user, Customer customer);

    void validatePutCustomer(User user, Customer customer);

    void addCustomerUser(User user, Customer customer);

    @PreAuthorize("hasAuthority('ADMIN')")
    void addEmployeeUser(User user, Employee employee);

    void updateCustomer(Customer customer);

    void updateEmployee(Employee employee);

    User getUserById(long id);

    User getUserByEmail(String email);

    Customer getCustomerByUser(long userId);

    Customer getCustomerById(long customerId);

    Employee getEmployeeByUser(long userId);

    @PreAuthorize("hasAuthority('ADMIN')")
    void enableUser(User user);

    @PreAuthorize("hasAuthority('ADMIN')")
    void disableUser(User user);

    @PreAuthorize("hasAnyAuthority('ADMIN','CONSULTANT')")
    List<Customer> getCustomers();

    @PreAuthorize("hasAnyAuthority('ADMIN','CONSULTANT')")
    List<Employee> getEmployees();
}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
