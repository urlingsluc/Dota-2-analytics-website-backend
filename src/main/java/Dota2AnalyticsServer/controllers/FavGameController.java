package Dota2AnalyticsServer.controllers;

import Dota2AnalyticsServer.exception.InvalidParametersException;
import Dota2AnalyticsServer.model.dto.FavoriteDTO;
import Dota2AnalyticsServer.services.FavGameService;
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
@RequestMapping(value = "/api/favgames/")
public class FavGameController {

    @Autowired
    private FavGameService favGameService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * gets all the favorite games of a user.
     * @param id player id
     * @return A list of game-id's
     */
    @GetMapping(value = "getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<FavoriteDTO> getFavoriteGames(@RequestParam Long id) {
        return favGameService.getAllGames(id).stream().map(p -> modelMapper.map(p,FavoriteDTO.class)).collect(Collectors.toList());
    }


    @PostMapping(value = "isfavorite",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean isFavorite(@RequestBody String data) throws InvalidParametersException {
        long myId = -1;
        long possibleFavGameID = -1;

        try {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            myId = json.get("id").getAsLong();
            possibleFavGameID = json.get("gameId").getAsLong();
        } catch (Exception e) {
            throw new InvalidParametersException();
        }
        return favGameService.isFavoriteGameCheck(myId, possibleFavGameID);
    }

    /**
     *
     * @param data
     * @return
     * @throws InvalidParametersException
     */
    @PostMapping(value = "updategame", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateFavoriteGame(@RequestBody String data) throws InvalidParametersException {
        long id = -1;
        long gameId = -1;

        try {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            System.out.println(json);
            id = json.get("id").getAsLong();
            gameId = json.get("gameId").getAsLong();
            System.out.println(gameId);

        } catch (Exception e) {
            throw new InvalidParametersException();
        }
        favGameService.updateFavoriteGame(id, gameId);
        return "succes";
    }
}
