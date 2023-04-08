package med.voll.api.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepo extends JpaRepository<User, Long> {


    UserDetails findByLogin(String login);
}
