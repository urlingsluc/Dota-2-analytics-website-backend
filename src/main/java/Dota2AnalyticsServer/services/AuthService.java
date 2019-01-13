package Dota2AnalyticsServer.services;

import Dota2AnalyticsServer.model.Credentials;
import Dota2AnalyticsServer.model.User;
import Dota2AnalyticsServer.repository.AuthRepository;
import Dota2AnalyticsServer.utilities.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public AuthService() {
    }

    /**
     *
     * @param name
     * @param email
     * @param username
     * @param password
     */
    public void createUser(String name, String email, String username, String password) {
        User user = new User(0L, name, email);
        Credentials credentials = new Credentials(0L, username, Hash.getHash(password));
        user.setCredentials(credentials);
        credentials.setUser(user);
        authRepository.save(user);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User loginUser(String username, String password) {
        User user = authRepository.getUserByCredentials_UsernameAndCredentials_Password(username, Hash.getHash(password));
        if (user == null) return null;
        user.createToken();
        authRepository.save(user);
        return user;
    }

    /**
     *
     * @param id
     * @param token
     * @return
     */
    public boolean checkToken(Long id, String token) {
        return authRepository.getUserByIdAndToken(id, token) == null;
    }
}