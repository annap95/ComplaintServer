package complaint.controller.user;

import complaint.controller.user.request.CredentialsRequest;
import complaint.controller.user.request.CustomerRegisterRequest;
import complaint.controller.user.request.EmployeeRegisterRequest;
import complaint.controller.user.response.TokenResponse;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.model.user.enums.UserRole;
import complaint.config.security.TokenService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/user/register/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@RequestHeader(value = "Authorization") String credentials,
                                 @RequestBody CustomerRegisterRequest customerRegisterRequest) {
        CredentialsRequest credentialsRequest = this.decodeCredentialsRequest(credentials);
        userService.validateRegister(credentialsRequest.getEmail());
        User user = User.builder()
                .email(credentialsRequest.getEmail())
                .password(passwordEncoder.encode(credentialsRequest.getPassword()))
                .userRole(UserRole.CUSTOMER)
                .enabled(true)
                .build();
        Customer customer = Customer.builder()
                .name(customerRegisterRequest.getName())
                .surname(customerRegisterRequest.getSurname())
                .streetName(customerRegisterRequest.getStreetName())
                .streetNumber(customerRegisterRequest.getStreetNumber())
                .postalCode(customerRegisterRequest.getPostalCode())
                .town(customerRegisterRequest.getTown())
                .phone(customerRegisterRequest.getPhone())
                .build();
        userService.addCustomerUser(user, customer);
    }

    @RequestMapping(value = "/user/register/employee", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerEmployee(@RequestHeader(value = "Authorization") String credentials,
                                 @RequestBody EmployeeRegisterRequest employeeRegisterRequest) {
        CredentialsRequest credentialsRequest = this.decodeCredentialsRequest(credentials);
        userService.validateRegister(credentialsRequest.getEmail());
        User user = User.builder()
                .email(credentialsRequest.getEmail())
                .password(passwordEncoder.encode(credentialsRequest.getPassword()))
                .userRole(employeeRegisterRequest.getUserRole())
                .enabled(true)
                .build();
        Employee employee = Employee.builder()
                .name(employeeRegisterRequest.getName())
                .surname(employeeRegisterRequest.getSurname())
                .build();
        userService.addEmployeeUser(user, employee);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestHeader(value = "Authorization") String credentials) {
        CredentialsRequest credentialsRequest = this.decodeCredentialsRequest(credentials);
        User user = userService.getUserByEmail(credentialsRequest.getEmail());
        if(!passwordEncoder.matches(credentialsRequest.getPassword(), user.getPassword()))
            throw new SecurityException("Authentication failed");
        return new TokenResponse(tokenService.generateToken(user));
    }

    private CredentialsRequest decodeCredentialsRequest(String credentials) {
        String[] credentialsArray = new String(Base64Utils.decode(credentials.getBytes())).split(":");
        return new CredentialsRequest(credentialsArray[0], credentialsArray[1]);
    }

}
