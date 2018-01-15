package complaint.service;

import complaint.model.user.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {

    void validateRegister(String email);

    void validateGetCustomer(User user, Customer customer);

    void validatePutCustomer(User user, Customer customer);

    void validatePutEmployee(User user, Employee employee);

    void validateChangeRole(User user);

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

    Employee getEmployeeById(long employeeId);

    @PreAuthorize("hasAuthority('ADMIN')")
    void enableUser(User user);

    @PreAuthorize("hasAuthority('ADMIN')")
    void disableUser(User user);

    @PreAuthorize("hasAnyAuthority('ADMIN','CONSULTANT')")
    List<Customer> getCustomers();

    @PreAuthorize("hasAnyAuthority('ADMIN','CONSULTANT')")
    List<Employee> getEmployees();
}
