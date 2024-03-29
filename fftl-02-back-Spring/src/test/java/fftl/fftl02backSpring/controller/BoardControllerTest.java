package fftl.fftl02backSpring.controller;

import com.google.gson.JsonObject;
import fftl.fftl02backSpring.advice.AdviceController;
import fftl.fftl02backSpring.config.security.JwtTokenProvider;
import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.service.BoardService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardService boardService;
    private UserService userService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    List<Board> boards;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(
            new BoardController(boardService))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))// utf-8 필터 추가
            .setControllerAdvice(AdviceController.class) //실제 Cotroller에서 발생하는 exception을 해결
            .build();

        boards = new ArrayList<>();
        boards.add(Board
            .builder()
            .id(1L)
            .title("첫글입니다.")
            .content("첫글에 어울리는 내용입니다.")
            .nickname("토마토")
            .regdate("2021-12-10")
            .user(null)
            .comments(null)
            .build());
        boards.add(Board
            .builder()
            .id(2L)
            .title("두번째글입니다.")
            .content("배가 고프네요")
            .nickname("수박이")
            .regdate("2021-12-10")
            .user(null)
            .comments(null)
            .build());
    }


    @DisplayName("게시글 전부 조회 테스트")
    @Test
    void getAllBoard() throws Exception{

        given(boardService.getAllBoard()).willReturn(boards);

        ResultActions actions =
            mvc.perform(
                get("/board")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"));
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("success").value(true))
            .andExpect(jsonPath("message").doesNotExist())
            .andDo(print());
    }

    @DisplayName("게시글 생성하기 테스트")
    @Test
    void saveBoard() throws Exception{
        given(boardService.saveBoard(any()))
            .willReturn(
                Board.builder()
                    .id(1L)
                    .title("제목이야")
                    .content("내용이야")
                    .regdate("2021-12-10")
                    .nickname("닉네임이야")
                    .build());

        JsonObject obj = new JsonObject();
        obj.addProperty("title", "제목이야");
        obj.addProperty("content", "내용이야");
        obj.addProperty("nickname", "닉네임이야");
        obj.addProperty("regdate", "2021-12-10");
        obj.addProperty("user_id", 1L);

        ResultActions actions =
            mvc.perform(
                post("/board")
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

    @DisplayName("게시글 수정하기 테스트")
    @Test
    void updateBoard() throws Exception{
        given(boardService.updateBoard(any(),any()))
            .willReturn(
                Board.builder()
                    .id(1L)
                    .title("제목일까?")
                    .content("내용일까?")
                    .regdate("2021-12-10")
                    .nickname("닉네임이야")
                    .build());

        JsonObject obj = new JsonObject();
        obj.addProperty("title", "제목이야");
        obj.addProperty("content", "내용이야");
        obj.addProperty("nickname", "닉네임이야");
        obj.addProperty("regdate", "2021-12-10");
        obj.addProperty("user_id", 1L);

        ResultActions actions =
            mvc.perform(
                patch("/board/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(String.valueOf(obj)));

        actions.andDo(print());
    }

    @DisplayName("삭제 테스트")
    @Test
    void deleteBoard() throws Exception{
        ResultActions actions =
            mvc.perform(
                delete("/board/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"));

        actions.andDo(print());
    }

    @DisplayName("아이디로 게시글 조회 테스트")
    @Test
    void getOneBoard() throws Exception{
        given(boardService.getOneBoard(any()))
            .willReturn(
                Board.builder()
                    .id(1L)
                    .title("제목이야")
                    .content("내용이야")
                    .regdate("2021-12-10")
                    .nickname("닉네임이야")
                    .build());

        ResultActions actions =
            mvc.perform(
                get("/board/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"));

        actions.andDo(print());

    }
}