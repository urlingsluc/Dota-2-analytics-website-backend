package Dota2AnalyticsServer.model.services;

import Dota2AnalyticsServer.model.data.User;
import Dota2AnalyticsServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    public UserService() {
    }

    public void updateSteamId32(Long id, int steamId32) {
        User userToUpdate = UserRepository.getOne(id);
        userToUpdate.setSteamId32(steamId32);
        UserRepository.save(userToUpdate);
    }
    public User getUserById(Long id) {
        return UserRepository.getUserById(id);
    }
}
