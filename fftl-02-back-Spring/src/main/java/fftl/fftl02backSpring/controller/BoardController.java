package fftl.fftl02backSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @GetMapping("a")
    public String getAllBoard(){
        return "";
    }

    @GetMapping("b")
    public String getBoard(){
        return "";
    }

    @PostMapping("c")
    public String addBoard(){
        return "";
    }

    @PutMapping("d")
    public String updateBoard(){
        return "";
    }
}
