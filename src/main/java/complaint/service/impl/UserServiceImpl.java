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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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
        return userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found"));
        // todo exception
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

    @Override
    public Employee getEmployeeByUser(long userId) {
        return employeeDao.findByUser(userId)
                .orElseThrow(() -> new RuntimeException("No employee found"));
        // todo exception
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), authorities);

        return userDetails;
    }
}
