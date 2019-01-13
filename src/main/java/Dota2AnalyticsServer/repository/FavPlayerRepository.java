package Dota2AnalyticsServer.repository;

import Dota2AnalyticsServer.model.FavoritePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavPlayerRepository extends JpaRepository<FavoritePlayer, Long> {
    /**
     *
     * @param id
     * @return
     */
    List<FavoritePlayer> getAllByUser_Id(Long id);

    /**
     *
     * @param id
     * @param FavId
     * @return
     */
    boolean existsFavoritePlayerByUser_IdAndFavId(long id, long FavId);
}