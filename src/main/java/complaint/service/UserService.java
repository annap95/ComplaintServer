package complaint.service;

import complaint.model.user.Customer;
import complaint.model.user.User;

/**
 * Created by anna on 18.09.17.
 */
public interface UserService {
    User getUserById(long id);
    User getUserByEmail(String email);
    void validateRegister(String email);
    void addUser(User user);

    Customer findCustomerByUserId(long userId);

}
