package Dota2AnalyticsServer.model.data;

import javax.persistence.*;

@Entity
@Table(name = "favorite_players")
public class FavoritePlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column
    private long favPlayerId;

    public long getFavPlayerId() {
        return favPlayerId;
    }

    public void setFavPlayerId(long favPlayerId) {
        this.favPlayerId = favPlayerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
