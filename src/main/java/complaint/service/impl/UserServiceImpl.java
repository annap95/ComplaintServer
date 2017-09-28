package complaint.service.impl;

import complaint.dao.user.CustomerDao;
import complaint.dao.user.EmployeeDao;
import complaint.dao.user.UserDao;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.model.user.enums.UserRole;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by anna on 18.09.17.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final CustomerDao customerDao;
    private final EmployeeDao employeeDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, CustomerDao customerDao, EmployeeDao employeeDao) {
        this.userDao = userDao;
        this.customerDao = customerDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email).orElseThrow(() -> new RuntimeException("No user found"));
        // or else throw TODO
    }

    //public Customer getCustomerByUser(User user)

    @Override
    public void validateRegister(String email) {
        if(userDao.getUserByEmail(email).isPresent())
            throw new SecurityException("User exists");
    }

    @Override
    public void addUser(User user) {
        userDao.persist(user);
        if(user.getUserRole() == UserRole.CUSTOMER) {
            Customer customer = new Customer();
            customer.setUser(user);
            customerDao.persist(customer);
        }
        else {
            Employee employee = new Employee();
            employee.setUser(user);
            employeeDao.persist(employee);
        }
    }

    @Override
    public Customer findCustomerByUserId(long userId) {
        return customerDao.findCustomerByUserId(userId);
    }
}
