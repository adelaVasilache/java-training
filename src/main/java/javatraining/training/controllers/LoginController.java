package javatraining.training.controllers;

import com.sun.media.sound.InvalidDataException;
import javatraining.training.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Adela Vasilache on 25.04.2018
 */
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login() throws NotFoundException, InvalidDataException {
        return new ResponseEntity<>("user authenticated", HttpStatus.OK);
    }
}
