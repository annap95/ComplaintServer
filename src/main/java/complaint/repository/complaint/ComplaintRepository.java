package complaint.repository.complaint;

import complaint.controller.complaint.request.ComplaintItemRequest;
import complaint.model.complaint.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

    Optional<Complaint> findByComplaintId(long complaintId);

    @Query("select c from Complaint c where " +
            "c.complaintId like concat_ws('%', '%', :#{#complaint.complaintId}, '%') " +
            "and upper(c.customer.name) like concat_ws('%', '%', upper(:#{#complaint.name}), '%') " +
            "and upper(c.customer.surname) like concat_ws('%', '%', upper(:#{#complaint.surname}), '%') " +
            "and upper(c.status) like concat_ws('%', '%', upper(:#{#complaint.status}), '%') ")
    Page<Complaint> findAll(Pageable pageable, @Param("complaint") ComplaintItemRequest complaint);

}
