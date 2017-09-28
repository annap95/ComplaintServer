package complaint.dao.user;

import complaint.model.user.Customer;

import java.util.Optional;

public interface CustomerDao {

    void persist(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);

    Customer findById(long id);
    Optional<Customer> findByUser(long userId);
}
