package complaint.dao.complaint;

import complaint.model.complaint.Complaint;

import java.util.List;

public interface ComplaintDao {

    void persist(Complaint complaint);
    void update(Complaint complaint);
    void delete(Complaint complaint);

    Complaint findById(long id);
    List<Complaint> findAll();
}
