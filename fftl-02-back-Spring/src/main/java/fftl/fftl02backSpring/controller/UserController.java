package fftl.fftl02backSpring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/")
@RestController
public class UserController {

    @PostMapping("/saveUser")
    public ResponseEntity<> saveUser(@RequestBody @Valid TransportationSaveDto transportationSaveDto){

        return "";
    }

    @PostMapping("/o")
    public String login(){

        return "";
    }

    @PostMapping("/p")
    public String loginCheck(){

        return "";
    }
}
