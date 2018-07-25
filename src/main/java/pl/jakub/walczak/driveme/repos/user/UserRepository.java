package pl.jakub.walczak.driveme.repos.user;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.user.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByEmailAndPassword(String email, String password);
}
