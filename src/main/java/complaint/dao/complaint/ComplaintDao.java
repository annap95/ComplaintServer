package complaint.dao.complaint;

import complaint.model.complaint.Complaint;

import java.util.List;

/**
 * Created by anna on 22.09.17.
 */
public interface ComplaintDao {

    Complaint find(long id);
    void persist(Complaint complaint);
    void update(Complaint complaint);
    void delete(Complaint complaint);

    List<Complaint> getComplaints();
}
