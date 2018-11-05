package main.repository;

import main.model.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    User getUserByCredentials_UsernameAndCredentials_Password(String username, String password);

    User getUserByIdAndToken(Long id, String token);
}


