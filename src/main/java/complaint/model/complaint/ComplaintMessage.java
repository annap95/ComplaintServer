package complaint.model.complaint;

import complaint.model.complaint.enums.Claim;
import complaint.model.complaint.enums.Decision;
import complaint.model.complaint.enums.MessageType;
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
@Table(name = "complaint_messages")
public class ComplaintMessage {

    /* all types */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complaint_message_id")
    private long complaintMessageId;

    @Column(name = "message")
    private String message;

    @Column(name = "claim")
    @Enumerated(EnumType.STRING)
    private Claim claim;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

    @Column(name = "message_type")
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    /* employee type */

    @Column(name = "decision")
    @Enumerated(EnumType.STRING)
    private Decision decision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
