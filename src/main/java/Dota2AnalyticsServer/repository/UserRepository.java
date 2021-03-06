package Dota2AnalyticsServer.repository;

import Dota2AnalyticsServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     *
     * @param id
     * @return
     */
    User getUserById(Long id);
}
