package complaint.repository.user;

import complaint.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByCustomerId(long customerId);

    @Query("select c from Customer where c.user.userId = :userId")
    Optional<Customer> findByUserId(@Param("userId") long userId);

}
