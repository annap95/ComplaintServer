package complaint.controller.user;

import complaint.controller.user.mapper.UserMapper;
import complaint.controller.user.request.CustomerRequest;
import complaint.controller.user.request.CustomerUserRequest;
import complaint.controller.user.request.EmployeeRequest;
import complaint.controller.user.request.EmployeeUserRequest;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserWriteController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserWriteController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/user/customer/{customerId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable(name = "customerId") long customerId,
                               @RequestBody CustomerRequest customerRequest, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Customer customer = userService.getCustomerById(customerId);
        userService.validatePutCustomer(loggedUser, customer);
        userMapper.mapCustomerRequestToCustomer(customerRequest, customer);
        userService.updateCustomer(customer);
    }

    @RequestMapping(value = "/user/customer/{customerId}/user", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomerUser(@PathVariable(name = "customerId") long customerId,
                                   @RequestBody CustomerUserRequest customerUserRequest,
                                   Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Customer customer = userService.getCustomerById(customerId);
        userService.validatePutCustomerUser(loggedUser);
        User user = customer.getUser();
        user.setEnabled(customerUserRequest.getEnabled());
        userService.updateUser(user);
    }

    @RequestMapping(value = "/user/employee/{employeeId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@PathVariable(name = "employeeId") long employeeId,
                               @RequestBody EmployeeRequest employeeRequest, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Employee employee = userService.getEmployeeById(employeeId);
        if(employeeRequest.getUserRole() != employee.getUser().getUserRole())
            userService.validatePutEmployeeUser(loggedUser, employee.getUser());
        userService.validatePutEmployee(loggedUser, employee);
        userMapper.mapEmployeeRequestToEmployee(employeeRequest, employee);
        userService.updateEmployee(employee);
        userService.updateUser(employee.getUser());
    }

    @RequestMapping(value = "/user/employee/{employeeId}/user", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployeeUser(@PathVariable(name = "employeeId") long employeeId,
                                   @RequestBody EmployeeUserRequest employeeUserRequest,
                                   Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Employee employee = userService.getEmployeeById(employeeId);
        userService.validatePutEmployeeUser(loggedUser, employee.getUser());
        User user = employee.getUser();
        user.setEnabled(employeeUserRequest.getEnabled());
        user.setUserRole(employeeUserRequest.getUserRole());
        userService.updateUser(user);
    }

}
