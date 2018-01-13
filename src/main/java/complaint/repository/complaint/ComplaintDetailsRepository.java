package complaint.repository.complaint;

import complaint.model.complaint.ComplaintDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintDetailsRepository extends JpaRepository<ComplaintDetails,Long> {
}
