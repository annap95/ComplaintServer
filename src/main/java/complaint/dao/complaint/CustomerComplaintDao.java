package complaint.dao.complaint;

import complaint.model.complaint.CustomerComplaint;

public interface CustomerComplaintDao {

    void persist(CustomerComplaint customerComplaint);
    void update(CustomerComplaint customerComplaint);
    void delete(CustomerComplaint customerComplaint);

    CustomerComplaint findById(long id);
}
