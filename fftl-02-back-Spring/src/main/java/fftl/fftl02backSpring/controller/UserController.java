package fftl.fftl02backSpring.controller;

import fftl.fftl02backSpring.advice.errors.SameDataExists;
import fftl.fftl02backSpring.config.security.JwtTokenProvider;
import fftl.fftl02backSpring.entity.User;
import fftl.fftl02backSpring.request.LoginUserDto;
import fftl.fftl02backSpring.request.SaveUserDto;
import fftl.fftl02backSpring.response.*;
import fftl.fftl02backSpring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/user")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/saveUser")
    public ResponseEntity<BasicResponse> join(@RequestBody SaveUserDto saveUserDto){
        if (userService.saveUser(saveUserDto)){
            return new ResponseEntity<>(new BasicResponse("success", "회원가입에 성공하였습니다."), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BasicResponse("false", "회원가입에 실패하였습니다."), HttpStatus.OK);
    }

    @PostMapping("/checkId")
    public ResponseEntity checkId(@RequestParam String username){
        System.out.println("function start : " + username);

        if(userService.findByUsername(username) != null){
            System.out.println("if in");
            throw new SameDataExists(ResponseMessage.SAME_ID);
        }
        System.out.println("if out");

        return new ResponseEntity(DefaultResponse.response(StatusCode.OK, ResponseMessage.SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/checkNickname")
    public ResponseEntity checkNickname(@RequestParam String nickname){
        System.out.println("function start : " + nickname);

        if(userService.findByNickname(nickname) != null){
            System.out.println("if in");
            throw new SameDataExists(ResponseMessage.SAME_NICKNAME);
        }
        System.out.println("if out");

        return new ResponseEntity(DefaultResponse.response(StatusCode.OK, ResponseMessage.SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto){
        User user = userService.findByUsername(loginUserDto.getUsername());
        System.out.println(loginUserDto.getUsername());
        System.out.println(loginUserDto.getPassword());
        if(user != null) {
            if (!user.getPassword().equals(loginUserDto.getPassword())) {
                throw new RuntimeException();
            }
        }

        String access_token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        System.out.println("point0 : " + access_token);

        return new ResponseEntity<>(new LoginResponse("true", access_token), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<MyInfoResponse> myInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user =  userService.findByUsername(username);
        String nickname = user.getNickname();
        Long uid = user.getUid();
        if(user == null){
            throw new RuntimeException();
        }
        return new ResponseEntity<>(new MyInfoResponse("true", "정보를 가져옵니다.", uid, username, nickname), HttpStatus.OK);
    }
}
