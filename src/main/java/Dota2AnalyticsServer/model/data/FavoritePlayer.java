package Dota2AnalyticsServer.model.data;

import javax.persistence.*;

@Entity
@Table(name = "favorite_players")
public class FavoritePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column
    private long favId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public long getFavId() {
        return favId;
    }

    public void setFavId(long favId) {
        this.favId = favId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FavoritePlayer() {
    }

    public FavoritePlayer(long id, long favId, User user) {
        this.id = id;
        this.favId = favId;
        this.user = user;
    }
}
