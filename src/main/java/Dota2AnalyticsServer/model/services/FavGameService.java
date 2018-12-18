package Dota2AnalyticsServer.model.services;

import Dota2AnalyticsServer.model.data.FavoriteGame;
import Dota2AnalyticsServer.model.data.User;
import Dota2AnalyticsServer.repository.FavGameRepository;
import Dota2AnalyticsServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavGameService {
    @Autowired
    private FavGameRepository favGameRepository;

    @Autowired
    private UserRepository userRepository;

    public FavGameService() {
    }

    /**
     *
     * @param playerId
     * @param gameId
     */
    public void updateFavoriteGame(long playerId, int gameId) {
        List<FavoriteGame> favGames = favGameRepository.getAllByUser_Id(playerId);
        Long exists = Long.valueOf(-1);
        for (FavoriteGame fg : favGames) {
            if (fg.getFavId() == gameId) exists = fg.getId();
        }

        if(exists.equals(Long.valueOf(-1))) {
            User user = userRepository.getOne(playerId);
            FavoriteGame favGame = new FavoriteGame(0L, gameId,user);
            favGameRepository.save(favGame);
        }
        else {
            favGameRepository.deleteById(exists);
        }
    }

    /**
     *
     * @param playerId
     * @return
     */
    public List<FavoriteGame> getAllGames(long playerId) {
        return favGameRepository.getAllByUser_Id(playerId);
    }
}