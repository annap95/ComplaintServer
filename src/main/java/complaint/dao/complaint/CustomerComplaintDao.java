package complaint.dao.complaint;

import complaint.model.complaint.CustomerComplaint;

/**
 * Created by anna on 22.09.17.
 */
public interface CustomerComplaintDao {

    CustomerComplaint find(long id);
    void persist(CustomerComplaint customerComplaint);
    void update(CustomerComplaint customerComplaint);
    void delete(CustomerComplaint customerComplaint);
}
