package complaint.controller.user.mapper;

import complaint.controller.user.request.CustomerRequest;
import complaint.controller.user.response.CustomerResponse;
import complaint.model.user.Customer;

public interface UserMapper {

    Customer mapCustomerRequestToCustomer(CustomerRequest customerRequest, Customer customer);

    CustomerResponse mapCustomerToCustomerResponse(Customer customer, CustomerResponse customerResponse);

}
