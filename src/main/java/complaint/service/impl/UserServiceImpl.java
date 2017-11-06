package complaint.service.impl;

import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.model.user.enums.UserRole;
import complaint.repository.user.CustomerRepository;
import complaint.repository.user.EmployeeRepository;
import complaint.repository.user.UserRepository;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomerRepository customerRepository,
                           EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void validateRegister(String email) {
        if(userRepository.findByEmail(email).isPresent())
            throw new SecurityException("User with this email already exists");
    }

    @Override
    public void addCustomerUser(User user, Customer customer) {
        User savedUser = userRepository.saveAndFlush(user);
        customer.setUser(savedUser);
        customerRepository.save(customer);
    }

    @Override
    public void addEmployeeUser(User user, Employee employee) {
        User savedUser = userRepository.saveAndFlush(user);
        employee.setUser(savedUser);
        employeeRepository.save(employee);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // todo exception
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // todo exception
    }

    @Override
    public Customer getCustomerByUser(long userId) {
        return customerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // todo exception
    }

    @Override
    public Employee getEmployeeByUser(long userId) {
        return employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        // todo exception
    }

    @Override
    public void enableUser(User user) {

    }

    @Override
    public void disableUser(User user) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), authorities);
    }
}
