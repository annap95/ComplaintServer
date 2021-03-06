package complaint.controller.user;

import complaint.controller.pagination.PaginationRequest;
import complaint.controller.pagination.PaginationResponse;
import complaint.controller.user.mapper.UserMapper;
import complaint.controller.user.request.CustomerItemRequest;
import complaint.controller.user.request.EmployeeItemRequest;
import complaint.controller.user.response.CustomerResponse;
import complaint.controller.user.response.EmployeeResponse;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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
    public EmployeeResponse getEmployee(@PathVariable(name = "employeeId") long employeeId) {
        Employee employee = userService.getEmployeeById(employeeId);
        // without validation
        EmployeeResponse employeeResponse = new EmployeeResponse();
        return userMapper.mapEmployeeToEmployeeResponse(employee, employeeResponse);
    }

    @RequestMapping(value = "/user/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponse getCustomers(@RequestBody PaginationRequest<CustomerItemRequest> paginationRequest) {
        CustomerItemRequest customerItemRequest = paginationRequest.getFilterOptions();
        if(customerItemRequest == null)
            customerItemRequest = new CustomerItemRequest();

        Page<Customer> page =
                userService.getCustomers(paginationRequest.mapToPageable(CustomerItemRequest.class), customerItemRequest);

        return PaginationResponse.builder()
                .totalItems(page.getTotalElements())
                .items(page.getContent()
                        .stream()
                        .map(userMapper::mapCustomerToCustomerItemResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    @RequestMapping(value = "/user/employee", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponse getEmployees(@RequestBody PaginationRequest<EmployeeItemRequest> paginationRequest) {
        EmployeeItemRequest employeeItemRequest = paginationRequest.getFilterOptions();
        if(employeeItemRequest == null)
            employeeItemRequest = new EmployeeItemRequest();

        Page<Employee> page =
                userService.getEmployees(paginationRequest.mapToPageable(EmployeeItemRequest.class), employeeItemRequest);

        return PaginationResponse.builder()
                .totalItems(page.getTotalElements())
                .items(page.getContent()
                        .stream()
                        .map(userMapper::mapEmployeeToEmployeeItemResponse)
                        .collect(Collectors.toList()))
                .build();
    }

}
