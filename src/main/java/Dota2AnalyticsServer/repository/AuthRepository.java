package Dota2AnalyticsServer.repository;

import Dota2AnalyticsServer.model.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    /**
     *
     * @param username
     * @param password
     * @return
     */
    User getUserByCredentials_UsernameAndCredentials_Password(String username, String password);

    /**
     *
     * @param id
     * @param token
     * @return
     */
    User getUserByIdAndToken(Long id, String token);
}