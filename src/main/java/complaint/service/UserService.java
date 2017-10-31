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


    // enable, disable users
    // users list for admin (maybe consultant)

    // employees registration for admin
}
