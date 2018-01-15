package complaint.controller.user.mapper;

import complaint.controller.user.request.CustomerRequest;
import complaint.controller.user.response.CustomerResponse;
import complaint.controller.user.response.EmployeeResponse;
import complaint.model.user.Customer;
import complaint.model.user.Employee;

public interface UserMapper {

    Customer mapCustomerRequestToCustomer(CustomerRequest customerRequest, Customer customer);

    CustomerResponse mapCustomerToCustomerResponse(Customer customer, CustomerResponse customerResponse);

    EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee, EmployeeResponse employeeResponse);
}
