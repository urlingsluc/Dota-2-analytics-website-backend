package main.controllers;

import com.google.gson.*;
import main.Exception.InvalidParametersException;
import main.model.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private AuthService authService;

}
