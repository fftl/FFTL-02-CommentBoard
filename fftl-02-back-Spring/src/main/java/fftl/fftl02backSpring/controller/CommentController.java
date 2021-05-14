package fftl.fftl02backSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @PostMapping("e")
    public String addComment(){
        return "";
    }

    @GetMapping("f")
    public String getAllComment(){
        return "";
    }


}
