package fftl.fftl02backSpring.controller;

import fftl.fftl02backSpring.advice.exciptions.BadRequest;
import fftl.fftl02backSpring.config.security.JwtTokenProvider;
import fftl.fftl02backSpring.dto.UserDto;
import fftl.fftl02backSpring.dto.response.Response;
import fftl.fftl02backSpring.dto.response.ResponseMessage;
import fftl.fftl02backSpring.entity.User;
import fftl.fftl02backSpring.dto.request.LoginUserRequset;
import fftl.fftl02backSpring.dto.request.SaveUserRequest;
import fftl.fftl02backSpring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/user")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    //user 생성
    @PostMapping("/saveUser")
    public Response saveUser(@RequestBody SaveUserRequest saveUserRequest){
        User user = userService.saveUser(saveUserRequest);
        UserDto userDto = UserDto.builder()
                .user_id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();

        return new Response(true, null, userDto);
    }

    //username 중복확인
    @PostMapping("/checkUsername")
    public Response checkUsername(@RequestParam String username){

        //이미 존재하는 유저가 있다면
        if(userService.findByUsername(username) != null){
            throw new BadRequest(ResponseMessage.SAME_ID);
        }

        return new Response(true, null, true);
    }

    //nickname 중복확인
    @PostMapping("/checkNickname")
    public Response checkNickname(@RequestParam String nickname){

        if(userService.findByNickname(nickname) != null){
            throw new BadRequest(ResponseMessage.SAME_NICKNAME);
        }

        return new Response(true, null, true);
    }

    //로그인
    @PostMapping("/login")
    public Response login(@RequestBody LoginUserRequset loginUserRequset){
        User user = userService.findByUsername(loginUserRequset.getUsername());

        //비밀번호가 다를 경우
        if (!user.getPassword().equals(loginUserRequset.getPassword())) {
            throw new BadRequest("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        String access_token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

        return new Response(true, null, access_token);
    }

    //로그인 유저 정보 가져오기
    @GetMapping("/profile")
    public Response myInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user =  userService.findByUsername(username);

        UserDto userDto = UserDto.builder()
                .user_id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();

        return new Response(true, null, userDto);
    }
}
