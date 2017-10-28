package complaint.model.complaint;

import complaint.model.complaint.enums.Claim;
import complaint.model.complaint.enums.Decision;
import complaint.model.user.Employee;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_complaint_messages")
public class EmployeeComplaintMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_complaint_message_id")
    private long employeeComplaintMessageId;

    @Column(name = "message")
    private String message;

    @Column(name = "claim")
    @Enumerated(EnumType.STRING)
    private Claim claim;

    @Column(name = "decision")
    @Enumerated(EnumType.STRING)
    private Decision decision;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

}
