package complaint.service;

import complaint.controller.user.request.CustomerItemRequest;
import complaint.controller.user.request.EmployeeItemRequest;
import complaint.model.user.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserService {

    /* validation */

    void validateRegister(String email);

    void validateGetCustomer(User user, Customer customer);

    void validatePutCustomer(User user, Customer customer);

    void validatePutEmployee(User user, Employee employee);

    void validatePutCustomerUser(User user);

    void validatePutEmployeeUser(User currentUser, User updatedUser);

    /* adding and updating */

    void addCustomerUser(User user, Customer customer);

    @PreAuthorize("hasAuthority('ADMIN')")
    void addEmployeeUser(User user, Employee employee);

    void updateUser(User user);

    void updateCustomer(Customer customer);

    void updateEmployee(Employee employee);

    /* getting */

    User getUserById(long id);

    User getUserByEmail(String email);

    Customer getCustomerByUser(long userId);

    Customer getCustomerById(long customerId);

    Employee getEmployeeByUser(long userId);

    Employee getEmployeeById(long employeeId);

    @PreAuthorize("hasAnyAuthority('ADMIN','CONSULTANT')")
    Page<Customer> getCustomers(Pageable pageable, CustomerItemRequest customerItemRequest);

    @PreAuthorize("hasAuthority('ADMIN')")
    Page<Employee> getEmployees(Pageable pageable, EmployeeItemRequest employeeItemRequest);
}
