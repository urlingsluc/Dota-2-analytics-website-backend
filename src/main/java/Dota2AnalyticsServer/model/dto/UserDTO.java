package Dota2AnalyticsServer.model.dto;

public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String token;

    private int steamId32;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSteamId32() {
        return steamId32;
    }

    public void setSteamId32(int steamId32) {
        this.steamId32 = steamId32;
    }
}
