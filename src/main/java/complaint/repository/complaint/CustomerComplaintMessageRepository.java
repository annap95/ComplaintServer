package complaint.repository.complaint;

import complaint.model.complaint.CustomerComplaintMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerComplaintMessageRepository extends JpaRepository<CustomerComplaintMessage,Long> {
}
