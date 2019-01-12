package Dota2AnalyticsServer.repository;

import Dota2AnalyticsServer.model.FavoriteGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavGameRepository extends JpaRepository<FavoriteGame, Long> {
    /**
     *
     * @param id
     * @return
     */
    List<FavoriteGame> getAllByUser_Id(Long id);

    boolean existsFavoriteGameByUser_IdAndFavId(long id, long FavId);
}
