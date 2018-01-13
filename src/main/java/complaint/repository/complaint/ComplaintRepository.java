package complaint.repository.complaint;

import complaint.model.complaint.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

    List<Complaint> findAll();

    Optional<Complaint> findByComplaintId(long complaintId);

}
