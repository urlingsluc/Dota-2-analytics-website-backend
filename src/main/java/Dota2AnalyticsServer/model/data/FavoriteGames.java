package Dota2AnalyticsServer.model.data;

import javax.persistence.*;

@Entity
@Table(name = "favorite_games")
public class FavoriteGames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column
    private long favGameId;

    public long getFavGameId() {
        return favGameId;
    }

    public void setFavGameId(long favGameId) {
        this.favGameId = favGameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
