package fftl.fftl02backSpring.controller;

import com.google.gson.JsonObject;
import fftl.fftl02backSpring.advice.AdviceController;
import fftl.fftl02backSpring.config.security.JwtTokenProvider;
import fftl.fftl02backSpring.entity.User;
import fftl.fftl02backSpring.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(
            new UserController(userService, jwtTokenProvider))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))// utf-8 필터 추가
            .setControllerAdvice(AdviceController.class) //실제 Cotroller에서 발생하는 exception을 해결
            .build();
    }

    @DisplayName("유저 생성 테스트")
    @Test
    void saveUser() throws Exception{
        given(userService.saveUser(any()))
            .willReturn(
                User.builder()
                    .id(1L)
                    .username("user")
                    .nickname("유저야")
                    .password("1234")
                    .build());

        JsonObject obj = new JsonObject();
        obj.addProperty("username", "user");
        obj.addProperty("password", "1234");

        ResultActions actions =
            mvc.perform(
                post("/user/saveUser")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(String.valueOf(obj)));
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("success").value(true))
            .andExpect(jsonPath("message").doesNotExist())
            .andExpect(jsonPath("$.data.username").value("user"))
            .andExpect(jsonPath("$.data.user_id").value("1"))
            .andExpect(jsonPath("$.data.nickname").value("유저야"));
    }

    @DisplayName("아이디 중복체크 테스트")
    @Test
    void checkUsername() throws Exception{
        given(userService.findByUsername("user")).willReturn(
                User.builder()
                    .id(1L)
                    .username("user")
                    .nickname("유저야")
                    .password("1234")
                    .build());

        ResultActions actions = mvc.perform(
            post("/user/checkUsername")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("username", "user"));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("success").value(false))
            .andExpect(jsonPath("message").exists())
            .andExpect(jsonPath("data").value(400));

        given(userService.findByUsername("user123")).willReturn(null);

        ResultActions actions2 =
            mvc.perform(
                post("/user/checkUsername")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .param("username","user123"));

        actions2
            .andExpect(status().isOk())
            .andExpect(jsonPath("success").value(true))
            .andExpect(jsonPath("message").doesNotExist())
            .andExpect(jsonPath("data").value(true));
    }

    @DisplayName("닉네임 중복체크 테스트")
    @Test
    void checkNickname() throws Exception{
        given(userService.findByNickname("유저야")).willReturn(
                User.builder()
                    .id(1L)
                    .username("user")
                    .nickname("유저야")
                    .password("1234")
                    .build());

        ResultActions actions = mvc.perform(
            post("/user/checkNickname")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("nickname", "유저야"));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("success").value(false))
            .andExpect(jsonPath("message").exists())
            .andExpect(jsonPath("data").value(400));

        //통과
        given(userService.findByUsername("누구야")).willReturn(null);

        ResultActions actions2 =
            mvc.perform(
                post("/user/checkNickname")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .param("nickname","누구야"));

        actions2
            .andExpect(status().isOk())
            .andExpect(jsonPath("success").value(true))
            .andExpect(jsonPath("message").doesNotExist())
            .andExpect(jsonPath("data").value(true));
    }

    @DisplayName("로그인 테스트")
    @Test
    void login() throws Exception{
        given(userService.findByUsername(any()))
            .willReturn(
                User.builder()
                    .id(1L)
                    .username("user")
                    .nickname("유저야")
                    .password("1234")
                    .build());

        JsonObject obj = new JsonObject();
        obj.addProperty("username", "user");
        obj.addProperty("password", "1234");

        ResultActions actions =
            mvc.perform(
                post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(String.valueOf(obj)));

        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("success").value(true))
            .andExpect(jsonPath("message").doesNotExist())
            .andDo(print());
    }

    //token을 이용한 테스트..
    @DisplayName("내 정보 조회 테스트")
    @Test
    void myInfo() throws Exception{
    }

}