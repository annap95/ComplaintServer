package complaint.model.complaint;

import complaint.model.complaint.enums.Claim;
import complaint.model.complaint.enums.Decision;

import javax.persistence.*;

/**
 * Created by anna on 22.09.17.
 */
@Entity
@Table(name = "employee_complaints")
public class EmployeeComplaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_complaint_id")
    private long employeeComplaintId;

    @Column(name = "decision")
    @Enumerated(EnumType.STRING)
    private Decision decision;

    @Column(name = "claim")
    @Enumerated(EnumType.STRING)
    private Claim claim;

    @Column(name = "justification")
    private String justification;
}
