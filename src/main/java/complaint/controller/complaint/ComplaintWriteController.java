package complaint.controller.complaint;

import complaint.controller.complaint.request.ComplaintAddRequest;
import complaint.controller.complaint.request.CustomerComplaintMessageAddRequest;
import complaint.controller.complaint.request.EmployeeComplaintMessageAddRequest;
import complaint.model.complaint.ComplaintDetails;
import complaint.model.complaint.CustomerComplaintMessage;
import complaint.model.complaint.EmployeeComplaintMessage;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import complaint.model.user.User;
import complaint.service.ComplaintService;
import complaint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ComplaintWriteController {

    private final UserService userService;
    private final ComplaintService complaintService;

    @Autowired
    public ComplaintWriteController(UserService userService, ComplaintService complaintService) {
        this.userService = userService;
        this.complaintService = complaintService;
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addComplaint(@RequestBody ComplaintAddRequest request, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Customer customer = userService.getCustomerByUser(loggedUser.getUserId());
        ComplaintDetails complaintDetails = ComplaintDetails.builder()
                .productDescription(request.getProductDescription())
                .invoiceNumber(request.getInvoiceNumber())
                .purchaseDate(request.getPurchaseDate())
                .price(request.getPrice())
                .iban(request.getIban())
                .build();
        CustomerComplaintMessage customerComplaintMessage = CustomerComplaintMessage.builder()
                .message(request.getMessage())
                .claim(request.getClaim())
                .date(new Date())
                .build();
        complaintService.addComplaint(customer, complaintDetails, customerComplaintMessage);
    }

    @RequestMapping(value = "/complaint/{complaintId}/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomerComplaintMessage(@PathVariable(name = "complaintId") long complaintId,
                                            @RequestBody CustomerComplaintMessageAddRequest request,
                                            Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Customer customer = userService.getCustomerByUser(loggedUser.getUserId());
        // todo
        CustomerComplaintMessage customerComplaintMessage = CustomerComplaintMessage.builder()
                .message(request.getMessage())
                .claim(request.getClaim())
                .date(new Date())
                .build();
        complaintService.addCustomerComplaintMessage(complaintId, customerComplaintMessage);

    }

    @RequestMapping(value = "/complaint/{complaintId}/employee", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployeeComplaintMessage(@PathVariable(name = "complaintId") long complaintId,
                                            @RequestBody EmployeeComplaintMessageAddRequest request,
                                            Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Employee employee = userService.getEmployeeByUser(loggedUser.getUserId());
        EmployeeComplaintMessage employeeComplaintMessage = EmployeeComplaintMessage.builder()
                .message(request.getMessage())
                .claim(request.getClaim())
                .decision(request.getDecision())
                .date(new Date())
                .employee(employee)
                .build();
        complaintService.addEmployeeComplaintMessage(complaintId, employeeComplaintMessage);
    }

}
