package Dota2AnalyticsServer.model.services;

import Dota2AnalyticsServer.model.data.FavoritePlayer;
import Dota2AnalyticsServer.model.data.User;
import Dota2AnalyticsServer.repository.FavPlayerRepository;
import Dota2AnalyticsServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavPlayerService {
    @Autowired
    private FavPlayerRepository favPlayerRepository;

    @Autowired
    private UserRepository userRepository;

    public FavPlayerService() {
    }

    /**
     *
     * @param playerId
     * @param favPlayerId
     */
    public void updateFavoritePlayer(long playerId, int favPlayerId) {
        List<FavoritePlayer> favPlayers = favPlayerRepository.getAllByUser_Id(playerId);
        Long exists = Long.valueOf(-1);
        for (FavoritePlayer fp : favPlayers) {
            if (fp.getFavId() == favPlayerId) exists = fp.getId();
        }

        if(exists.equals(Long.valueOf(-1))) {
            User user = userRepository.getOne(playerId);
            FavoritePlayer favPlayer = new FavoritePlayer(0L, favPlayerId,user);
            favPlayerRepository.save(favPlayer);
        }
        else {
            favPlayerRepository.deleteById(exists);
        }
    }

    /**
     *
     * @param playerId
     * @return
     */
    public List<FavoritePlayer> getAllPlayers(Long playerId) {
        return favPlayerRepository.getAllByUser_Id(playerId);
    }

    public boolean isFavoritePlayerCheck(long myId, int possibleFavPlayerID) {
        return favPlayerRepository.existsFavoritePlayerByUser_IdAndFavId(myId,possibleFavPlayerID);
    }
}
