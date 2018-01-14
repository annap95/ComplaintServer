<<<<<<< HEAD
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
=======
package complaint.repository.user;

import complaint.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByCustomerId(long customerId);

    @Query("select c from Customer c where c.user.userId = :userId")
    Optional<Customer> findByUserId(@Param("userId") long userId);

    List<Customer> findAll();

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
