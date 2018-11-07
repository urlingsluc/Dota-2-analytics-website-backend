package Dota2AnalyticsServer.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Dota2AnalyticsServer.Exception.InvalidParametersException;
import Dota2AnalyticsServer.Exception.NotFoundException;
import Dota2AnalyticsServer.model.services.AuthService;
import Dota2AnalyticsServer.model.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String insertUser(@RequestBody String data) throws InvalidParametersException {

        String name = "";
        String email = "";
        String username = "";
        String password = "";

        try {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            name = json.get("name").getAsString();
            email = json.get("email").getAsString();
            username = json.get("username").getAsString();
            password = json.get("password").getAsString();
        } catch (Exception e) {
            throw new InvalidParametersException();
        }

        authService.createUser(name, email, username, password);

        return "succes";
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User loginUser(@RequestBody String data) throws NotFoundException, InvalidParametersException {

        String name = "";
        String pass = "";
        try {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            name = json.get("username").getAsString();
            pass = json.get("password").getAsString();
        } catch (Exception e) {
            throw new InvalidParametersException();
        }

        User user = authService.loginUser(name, pass);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @PostMapping(value = "token", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getToken(@RequestBody String data) throws NotFoundException, InvalidParametersException {

        long id = -1;
        String token = "";
        try {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            id = json.get("id").getAsLong();
            token = json.get("token").getAsString();
        } catch (Exception e) {
            throw new InvalidParametersException();
        }

        if (authService.checkToken(id, token)){
            throw new NotFoundException();
        }
        return "succes";
    }


}
