package Dota2AnalyticsServer.controllers;

import Dota2AnalyticsServer.exception.InvalidParametersException;
import Dota2AnalyticsServer.model.dto.UserDTO;
import Dota2AnalyticsServer.services.UserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService UserService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     *
     * @param data
     * @return
     * @throws InvalidParametersException
     */
    @PostMapping(path = "setsteamid", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String setSteamid(@RequestBody String data) throws InvalidParametersException {

        long id = -1;
        int steamId32 = -1;

        try {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            id = json.get("id").getAsLong();
            steamId32 = json.get("steamId32").getAsInt();
        } catch (Exception e) {
            throw new InvalidParametersException();
        }

        UserService.updateSteamId32(id,steamId32);
        return "succes";
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "getuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserDTO getUserById(@RequestParam Long id) {
        return modelMapper.map(UserService.getUserById(id),UserDTO.class);
    }
}
