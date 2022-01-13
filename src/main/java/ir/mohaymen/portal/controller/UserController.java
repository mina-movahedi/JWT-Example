package ir.mohaymen.portal.controller;

import ir.mohaymen.portal.entity.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(path = "Login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public boolean getLoginPermit(@RequestBody UserDto userDto){
        System.out.println("username: " + userDto.getUsername());
        System.out.println("password: " + userDto.getPassword());
        return true;
    }

}
