package complaint.dao.complaint;

import complaint.model.complaint.CustomerComplaint;

import java.util.Optional;

public interface CustomerComplaintDao {

    void persist(CustomerComplaint customerComplaint);
    void update(CustomerComplaint customerComplaint);
    void delete(CustomerComplaint customerComplaint);

    Optional<CustomerComplaint> findById(long id);
}
