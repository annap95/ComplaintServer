package complaint.controller.user;

import complaint.controller.user.request.CredentialsRequest;
import complaint.controller.user.request.EmployeeRequest;
import complaint.controller.user.response.TokenResponse;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.model.user.enums.UserRole;
import complaint.config.security.TokenService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserLoginRegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public UserLoginRegisterController(UserService userService,
                                       PasswordEncoder passwordEncoder,
                                       TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/auth/register/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@RequestHeader(value = "Authorization") String credentials) {
        CredentialsRequest credentialsRequest = this.decodeCredentialsRequest(credentials);
        userService.validateRegister(credentialsRequest.getEmail());
        User user = User.builder()
                .email(credentialsRequest.getEmail())
                .password(passwordEncoder.encode(credentialsRequest.getPassword()))
                .userRole(UserRole.CUSTOMER)
                .enabled(true)
                .build();
        Customer customer = new Customer();
        customer.initWithEmptyValues();
        userService.addCustomerUser(user, customer);
    }

    @RequestMapping(value = "/auth/register/employee", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerEmployee(@RequestHeader(value = "Authorization") String credentials,
                                 @RequestBody EmployeeRequest employeeRequest,
                                 Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        userService.validateEmployeeRegister(loggedUser);
        CredentialsRequest credentialsRequest = this.decodeCredentialsRequest(credentials);
        userService.validateRegister(credentialsRequest.getEmail());
        User user = User.builder()
                .email(credentialsRequest.getEmail())
                .password(passwordEncoder.encode(credentialsRequest.getPassword()))
                .userRole(employeeRequest.getUserRole())
                .enabled(true)
                .build();
        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .surname(employeeRequest.getSurname())
                .build();
        userService.addEmployeeUser(user, employee);
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestHeader(value = "Authorization") String credentials) {
        CredentialsRequest credentialsRequest = this.decodeCredentialsRequest(credentials);
        User user = userService.getUserByEmail(credentialsRequest.getEmail());
        if(!passwordEncoder.matches(credentialsRequest.getPassword(), user.getPassword()) || !user.isEnabled())
            throw new SecurityException("Authentication failed");
        Long customerId = null;
        Long employeeId = null;
        if(user.isEmployee())
            employeeId = userService.getEmployeeByUser(user.getUserId()).getEmployeeId();
        else
            customerId = userService.getCustomerByUser(user.getUserId()).getCustomerId();
        return new TokenResponse(tokenService.generateToken(user), user.getUserRole(),
                                 user.getUserId(), customerId, employeeId);
    }

    private CredentialsRequest decodeCredentialsRequest(String credentials) {
        String[] credentialsArray = new String(Base64Utils.decode(credentials.getBytes())).split(":");
        return new CredentialsRequest(credentialsArray[0], credentialsArray[1]);
    }

}
