package complaint.controller.user.mapper.impl;

import complaint.controller.user.mapper.UserMapper;
import complaint.controller.user.request.CustomerRequest;
import complaint.controller.user.request.EmployeeRequest;
import complaint.controller.user.response.CustomerResponse;
import complaint.controller.user.response.EmployeeResponse;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public Customer mapCustomerRequestToCustomer(CustomerRequest customerRequest, Customer customer) {
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customer.setStreetName(customerRequest.getStreetName());
        customer.setStreetNumber(customerRequest.getStreetNumber());
        customer.setPostalCode(customerRequest.getPostalCode());
        customer.setTown(customerRequest.getTown());
        customer.setPhone(customerRequest.getPhone());
        return customer;
    }

    @Override
    public CustomerResponse mapCustomerToCustomerResponse(Customer customer, CustomerResponse customerResponse) {
        customerResponse.setCustomerId(customer.getCustomerId());
        customerResponse.setName(customer.getName());
        customerResponse.setSurname(customer.getSurname());
        customerResponse.setStreetName(customer.getStreetName());
        customerResponse.setStreetNumber(customer.getStreetNumber());
        customerResponse.setPostalCode(customer.getPostalCode());
        customerResponse.setTown(customer.getTown());
        customerResponse.setPhone(customer.getPhone());
        return customerResponse;
    }

    @Override
    public Employee mapEmployeeRequestToEmployee(EmployeeRequest employeeRequest, Employee employee) {
        employee.setName(employeeRequest.getName());
        employee.setSurname(employeeRequest.getSurname());
        employee.getUser().setUserRole(employeeRequest.getUserRole());
        return employee;
    }

    @Override
    public EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee, EmployeeResponse employeeResponse) {
        employeeResponse.setEmployeeId(employee.getEmployeeId());
        employeeResponse.setName(employee.getName());
        employeeResponse.setSurname(employee.getSurname());
        employeeResponse.setRole(employee.getUser().getUserRole());
        return employeeResponse;
    }
}
