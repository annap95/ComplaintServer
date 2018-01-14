<<<<<<< HEAD
package complaint.repository.user;

import complaint.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserId(long userId);

    Optional<User> findByEmail(String email);

}
=======
package complaint.repository.user;

import complaint.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserId(long userId);

    Optional<User> findByEmail(String email);

}
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe
