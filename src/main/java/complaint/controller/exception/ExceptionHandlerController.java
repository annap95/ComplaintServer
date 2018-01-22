package complaint.controller.exception;

import complaint.exception.ComplaintNotFoundException;
import complaint.exception.CustomerNotFoundException;
import complaint.exception.EmployeeNotFoundException;
import complaint.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleSecurityException() {
        return "SecurityException";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException() {
        return "IllegalArgumentException";
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException() {
        return "UserNotFoundException";
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFoundException() {
        return "CustomerNotFoundException";
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeerNotFoundException() {
        return "EmployeeNotFoundException";
    }

    @ExceptionHandler(ComplaintNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleComplaintNotFoundException() {
        return "ComplaintNotFoundException";
    }
}
