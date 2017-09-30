package complaint.dao.user;

import complaint.model.user.User;

import java.util.Optional;

public interface UserDao {

    void persist(User user);
    void update(User user);
    void delete(User user);

    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
}
