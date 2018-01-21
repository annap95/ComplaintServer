package complaint.repository.complaint;

import complaint.model.complaint.ComplaintMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintMessageRepository extends JpaRepository<ComplaintMessage,Long> {

}
