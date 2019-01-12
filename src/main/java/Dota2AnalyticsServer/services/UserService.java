package Dota2AnalyticsServer.services;

import Dota2AnalyticsServer.model.User;
import Dota2AnalyticsServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    public UserService() {
    }

    /**
     *
     * @param id
     * @param steamId32
     */
    public void updateSteamId32(Long id, int steamId32) {
        User userToUpdate = UserRepository.getOne(id);
        userToUpdate.setSteamId32(steamId32);
        UserRepository.save(userToUpdate);
    }

    /**
     *
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        return UserRepository.getUserById(id);
    }
}
