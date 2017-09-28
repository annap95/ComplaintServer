package complaint.dao.user;

import complaint.model.user.Customer;

/**
 * Created by anna on 22.09.17.
 */
public interface CustomerDao {

    Customer find(long id);
    void persist(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);

    Customer findCustomerByUserId(long userId);
}
