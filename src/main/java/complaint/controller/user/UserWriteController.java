package complaint.controller.user;

import complaint.controller.user.mapper.UserMapper;
import complaint.controller.user.request.CustomerRequest;
import complaint.controller.user.request.EmployeeRequest;
import complaint.model.user.Customer;
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

    @RequestMapping(value = "/user/employee", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@RequestBody EmployeeRequest employeeRequest, Authentication authentication) {

    }

}
