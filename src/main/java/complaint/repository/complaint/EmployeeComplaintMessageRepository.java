package complaint.repository.complaint;

import complaint.model.complaint.EmployeeComplaintMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeComplaintMessageRepository extends JpaRepository<EmployeeComplaintMessage,Long> {
}
