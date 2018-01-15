package complaint.controller.user;

import complaint.controller.user.mapper.UserMapper;
import complaint.controller.user.response.CustomerResponse;
import complaint.controller.user.response.EmployeeResponse;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserReadController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserReadController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/user/customer/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomer(@PathVariable(name = "customerId") long customerId,
                                        Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Customer customer = userService.getCustomerById(customerId);
        userService.validateGetCustomer(loggedUser, customer);
        CustomerResponse customerResponse = new CustomerResponse();
        return userMapper.mapCustomerToCustomerResponse(customer, customerResponse);
    }

    @RequestMapping(value = "/user/employee/{employeeId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployee(@PathVariable(name = "employeeId") long employeeId,
                                        Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Employee employee = userService.getEmployeeById(employeeId);
        // without validation
        EmployeeResponse employeeResponse = new EmployeeResponse();
        return userMapper.mapEmployeeToEmployeeResponse(employee, employeeResponse);
    }

    @RequestMapping(value = "/user/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getCustomers(Authentication authentication) {
        return null;
    }

    @RequestMapping(value = "/user/employee", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getEmployees(Authentication authentication) {
        return null;
    }

}
