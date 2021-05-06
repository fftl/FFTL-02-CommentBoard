package fftl.fftl02backSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @GetMapping("")
    public String getAllBoard(){
        return "";
    }

    @GetMapping("")
    public String getBoard(){
        return "";
    }

    @PostMapping("")
    public String addBoard(){
        return "";
    }

    @PutMapping("")
    public String updateBoard(){
        return "";
    }
}
