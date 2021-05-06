package fftl.fftl02backSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @PostMapping("")
    public String addComment(){
        return "";
    }

    @GetMapping("")
    public String getAllComment(){
        return "";
    }

    
}
