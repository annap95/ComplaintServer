package complaint.service.impl;

import complaint.controller.user.request.CustomerItemRequest;
import complaint.controller.user.request.EmployeeItemRequest;
import complaint.exception.CustomerNotFoundException;
import complaint.exception.EmployeeNotFoundException;
import complaint.exception.UserNotFoundException;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.model.user.enums.UserRole;
import complaint.repository.user.CustomerRepository;
import complaint.repository.user.EmployeeRepository;
import complaint.repository.user.UserRepository;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void validateEmployeeRegister(User user) {
        if(!user.isAdmin())
            throw new SecurityException("Authorization failed");
    }

    @Override
    public void validateGetCustomer(User user, Customer customer) {
        if(!user.isEmployee() && customer.getUser().getUserId() != user.getUserId())
            throw new SecurityException("Authorization failed");
    }

    @Override
    public void validatePutCustomer(User user, Customer customer) {
        if(customer.getUser().getUserId() != user.getUserId())
            throw new SecurityException("Authorization failed");
    }

    @Override
    public void validatePutEmployee(User user, Employee employee) {
        if(employee.getUser().getUserId() != user.getUserId())
            throw new SecurityException("Authorization failed");
    }

    @Override
    public void validatePutCustomerUser(User user) {
        if(!user.isEmployee())
            throw new SecurityException("Authorization failed");
    }

    @Override
    public void validatePutEmployeeUser(User currentUser, User updatedUser) {
        if(!currentUser.isEmployee())
            throw new SecurityException("Authorization failed");
        if(currentUser.getUserId() == updatedUser.getUserId())
            throw new SecurityException("Authorization failed");
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
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findByUserId(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Customer getCustomerByUser(long userId) {
        return customerRepository.findByUserId(userId)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Customer getCustomerById(long customerId) {
        return customerRepository.findByCustomerId(customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Employee getEmployeeByUser(long userId) {
        return employeeRepository.findByUserId(userId)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(CustomerNotFoundException::new);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable, CustomerItemRequest customerItemRequest) {
        return customerRepository.findAll(pageable, customerItemRequest);
    }

    @Override
    public Page<Employee> getEmployees(Pageable pageable, EmployeeItemRequest employeeItemRequest) {
        return employeeRepository.findAll(pageable, employeeItemRequest);
    }
}
