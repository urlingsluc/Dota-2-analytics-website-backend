package Dota2AnalyticsServer.model.services;

import Dota2AnalyticsServer.model.data.Credentials;
import Dota2AnalyticsServer.model.data.User;
import Dota2AnalyticsServer.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public AuthService() {
    }

    public void createUser(String name, String email, String username, String password){
        User user = new User();
        user.setId(0L);
        user.setEmail(email);
        user.setName(name);
        user.createToken();

        Credentials credentials = new Credentials();
        credentials.setId(0L);
        credentials.setUsername(username);
        credentials.setPassword(password);

        user.setCredentials(credentials);
        credentials.setUser(user);
        authRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = authRepository.getUserByCredentials_UsernameAndCredentials_Password(username, password);
        if (user == null) return null;
        user.createToken();
        authRepository.save(user);
        return user;
    }

    public boolean checkToken(Long id, String token) {
        return authRepository.getUserByIdAndToken(id, token) == null;
    }
}