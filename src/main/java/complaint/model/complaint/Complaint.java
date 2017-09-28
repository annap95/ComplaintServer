package complaint.model.complaint;

import complaint.model.complaint.enums.ComplaintStatus;
import complaint.model.user.Customer;
import complaint.model.user.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complaint_id")
    private long complaintId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_complaint_id")
    private CustomerComplaint customerComplaint;

    @Column(name = "submit_date")
    private Date submitDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_complaint_id")
    private EmployeeComplaint employeeComplaint;

    @Column(name = "consider_date")
    private Date considerDate;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "acceptance_date")
    private Date acceptanceDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;


    public Complaint(Customer customer, Employee employee, CustomerComplaint customerComplaint,
                     Date submitDate, EmployeeComplaint employeeComplaint, Date considerDate,
                     boolean accepted, Date acceptanceDate, ComplaintStatus status) {
        this.customer = customer;
        this.employee = employee;
        this.customerComplaint = customerComplaint;
        this.submitDate = submitDate;
        this.employeeComplaint = employeeComplaint;
        this.considerDate = considerDate;
        this.accepted = accepted;
        this.acceptanceDate = acceptanceDate;
        this.status = status;
    }
}
