package complaint.model.complaint;

import complaint.model.complaint.enums.Claim;
import complaint.model.complaint.enums.Decision;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
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


    public EmployeeComplaint(Decision decision, Claim claim, String justification) {
        this.decision = decision;
        this.claim = claim;
        this.justification = justification;
    }
}
