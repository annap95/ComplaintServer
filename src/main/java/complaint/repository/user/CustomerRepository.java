package complaint.repository.user;

import complaint.controller.user.request.CustomerItemRequest;
import complaint.model.user.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByCustomerId(long customerId);

    @Query("select c from Customer c where c.user.userId = :userId")
    Optional<Customer> findByUserId(@Param("userId") long userId);

    @Query("select c from Customer c where " +
            "c.customerId like concat_ws('%', :#{#customer.customerId}, '%') " +
            "and upper(c.name) like concat_ws('%', upper(:#{#customer.name}), '%') " +
            "and upper(c.surname) like concat_ws('%', upper(:#{#customer.surname}), '%') " +
            "and upper(c.streetName) like concat_ws('%', upper(:#{#customer.streetName}), '%') " +
            "and upper(c.streetNumber) like concat_ws('%', upper(:#{#customer.streetNumber}), '%') " +
            "and upper(c.postalCode) like concat_ws('%', upper(:#{#customer.postalCode}), '%') " +
            "and upper(c.town) like concat_ws('%', upper(:#{#customer.town}), '%') " +
            "and upper(c.phone) like concat_ws('%', upper(:#{#customer.phone}), '%') ")
    Page<Customer> findAll(Pageable pageable, @Param("customer") CustomerItemRequest customer);

}

/*
    private String email;
    private boolean enabled;
 */
