package complaint.dao.user;

import complaint.model.user.User;

import java.util.Optional;

/**
 * Created by anna on 18.09.17.
 */
public interface UserDao {

    User find(long id);
    void persist(User user);
    void update(User user);
    void delete(User user);

    Optional<User> getUserByEmail(String email);
}
