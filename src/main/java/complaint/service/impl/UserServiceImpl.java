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
    public void validateRegister(String email) {
        if(userDao.findByEmail(email).isPresent())
            throw new SecurityException("User exists");
    }

    @Override
    public void addUser(User user) {
        userDao.persist(user);
        if(user.getUserRole() == UserRole.CUSTOMER)
            customerDao.persist(Customer.builder()
                    .user(user)
                    .build());
        else
            employeeDao.persist(Employee.builder()
                    .user(user)
                    .build());
    }

    @Override
    public User getUserById(long id) {
        return userDao.findById(id);
        // todo null
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found"));
        // todo exception
    }

    @Override
    public Customer getCustomerByUser(long userId) {
        return customerDao.findByUser(userId)
                .orElseThrow(() -> new RuntimeException("No customer found"));
        // todo exception
    }
}
