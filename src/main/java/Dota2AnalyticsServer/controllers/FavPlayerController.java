package Dota2AnalyticsServer.controllers;

import Dota2AnalyticsServer.Exception.InvalidParametersException;
import Dota2AnalyticsServer.model.dto.FavoriteDTO;
import Dota2AnalyticsServer.model.services.FavPlayerService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/favplayers/")
public class FavPlayerController {

    @Autowired
    private FavPlayerService favPlayerService;

    @Autowired
    private ModelMapper modelMapper;
    /**
     * gets all the favorite players of a user.
     * @param id player id
     * @return A list of player-id's
     */
    @GetMapping(value = "getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<FavoriteDTO> getFavoritePlayers(@RequestParam Long id) {
        return favPlayerService.getAllPlayers(id).stream().map(p -> modelMapper.map(p, FavoriteDTO.class)).collect(Collectors.toList());
    }

    @PostMapping(value = "isfavorite",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean isFavorite(@RequestBody String data) throws InvalidParametersException {
        long myId = -1;
        int possibleFavPlayerID = -1;

        try {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            System.out.println(json.toString());
            myId = json.get("id").getAsLong();
            possibleFavPlayerID = json.get("playerId").getAsInt();
        } catch (Exception e) {
            throw new InvalidParametersException();
        }
        return favPlayerService.isFavoritePlayerCheck(myId, possibleFavPlayerID);
    }

    /**
     *
     * @param data
     * @return
     * @throws InvalidParametersException
     */
    @PostMapping(value = "updatePlayer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updatePlayer(@RequestBody String data) throws InvalidParametersException {
        long id = -1;
        int favPlayerId = -1;

        try {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            id = json.get("id").getAsLong();
            favPlayerId = json.get("playerId").getAsInt();
        } catch (Exception e) {
            throw new InvalidParametersException();
        }
        favPlayerService.updateFavoritePlayer(id, favPlayerId);
        return "succes";
    }
}
