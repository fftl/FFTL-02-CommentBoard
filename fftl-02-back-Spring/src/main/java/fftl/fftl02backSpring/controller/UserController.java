package fftl.fftl02backSpring.controller;

import fftl.fftl02backSpring.request.SaveUserDto;
import fftl.fftl02backSpring.response.BasicResponse;
import fftl.fftl02backSpring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/user")
@RestController
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<BasicResponse> join(@RequestBody SaveUserDto saveUserDto){
        if (userService.saveUser(saveUserDto)){
            return new ResponseEntity<>(new BasicResponse("success", "회원가입에 성공하였습니다."), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BasicResponse("false", "회원가입에 실패하였습니다."), HttpStatus.OK);
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
