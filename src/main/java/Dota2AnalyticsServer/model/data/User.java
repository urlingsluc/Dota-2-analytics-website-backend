package Dota2AnalyticsServer.model.data;

import Dota2AnalyticsServer.model.logic.TokenGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column
    private String name;

    @Column
    @Email
    private String email;

    @Column
    private String token;

    @Column
    private int steamId32;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<FavoriteGame> favoriteGames;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<FavoritePlayer> favoritePlayers;

    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Credentials credentials;

//    public User(String name, String email) {
//        super();
//        this.name = name;
//        this.email = email;
//    }

    public User() {
    }

    public User(Long id, String name, @Email String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        createToken();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FavoriteGame> getFavoriteGames() {
        return favoriteGames;
    }

    public void setFavoriteGames(List<FavoriteGame> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }

    public List<FavoritePlayer> getFavoritePlayers() {
        return favoritePlayers;
    }

    public void setFavoritePlayers(List<FavoritePlayer> favoritePlayers) {
        this.favoritePlayers = favoritePlayers;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public long getSteamId32() {
        return steamId32;
    }

    public void setSteamId32(int steamId32) {
        this.steamId32 = steamId32;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     *
     */
    public void createToken() {
        token = TokenGenerator.createToken();
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
