package main.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.model.logic.TokenGenerator;

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

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private List<Favorite> favorites;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Credentials credentials;

    public User(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public User() {

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

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

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