package complaint.controller.user;

import complaint.controller.user.request.CredentialsRequest;
import complaint.controller.user.response.TokenResponse;
import complaint.model.user.User;
import complaint.model.user.enums.UserRole;
import complaint.security.TokenService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by anna on 18.09.17.
 */

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

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestHeader(value = "Authorization") String credentials) {
        CredentialsRequest credentialsRequest = this.decodeCredentialsRequest(credentials);
        userService.validateRegister(credentialsRequest.getEmail());
        User user = new User(credentialsRequest.getEmail(), passwordEncoder.encode(credentialsRequest.getPassword()), UserRole.CUSTOMER);
        userService.addUser(user);
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




//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public String getString() {
//        return "It works";
//    }
}
