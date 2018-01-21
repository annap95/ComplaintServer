package complaint.model.complaint;

import complaint.model.complaint.enums.ComplaintStatus;
import complaint.model.user.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complaint_id")
    private long complaintId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "complaint")
    private ComplaintDetails complaintDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "complaint")
    private List<CustomerComplaintMessage> customerComplaintMessages;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "complaint")
    private List<EmployeeComplaintMessage> employeeComplaintMessages;

    @Column(name = "submit_date")
    private Date submitDate;

    @Column(name = "consider_date")
    private Date considerDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    // current / final claim

}
