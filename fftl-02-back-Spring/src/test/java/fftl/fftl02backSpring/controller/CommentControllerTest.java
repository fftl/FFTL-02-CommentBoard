package fftl.fftl02backSpring.controller;

import com.google.gson.JsonObject;
import fftl.fftl02backSpring.advice.AdviceController;
import fftl.fftl02backSpring.config.security.JwtTokenProvider;
import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.entity.Comment;
import fftl.fftl02backSpring.entity.User;
import fftl.fftl02backSpring.service.BoardService;
import fftl.fftl02backSpring.service.CommentService;
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
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;
    private BoardService boardService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    List<Comment> comments;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(
            new CommentController(commentService))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))// utf-8 필터 추가
            .setControllerAdvice(AdviceController.class) //실제 Cotroller에서 발생하는 exception을 해결
            .build();

        comments = new ArrayList<>();
        comments.add(Comment.builder()
            .nickname("닉네임이야")
            .comment("내용이야")
            .id(1L)
            .user(User.builder()
                .username("user")
                .password("1234")
                .id(1L)
                .nickname("닉네임이야")
                .joinDate("2021-12-11")
                .comments(null)
                .boards(null)
                .build())
            .board(Board.builder()
                .nickname("닉네임이야")
                .comments(null)
                .id(1L)
                .user(null)
                .regdate("2021-12-21")
                .title("제목이야")
                .content("내용이야")
                .build())
            .build());

        comments.add(Comment.builder()
            .nickname("닉네임이야222")
            .comment("내용이야333")
            .id(2L)
            .user(User.builder()
                .username("user2")
                .password("12342")
                .id(1L)
                .nickname("닉네임이야2")
                .joinDate("2021-12-11")
                .comments(null)
                .boards(null)
                .build())
            .board(Board.builder()
                .nickname("닉네임이야2")
                .comments(null)
                .id(1L)
                .user(null)
                .regdate("2021-12-21")
                .title("제목이야2")
                .content("내용이야2")
                .build())
            .build());
    }

    @DisplayName("댓글 추가하기 테스트")
    @Test
    void addComment() throws Exception {
        given(commentService.saveComment(any())).willReturn(comments.get(0));

        JsonObject obj = new JsonObject();
        obj.addProperty("nickname", "닉네임이야");
        obj.addProperty("comment", "내용이야");
        obj.addProperty("regdate", "2021-12-11");
        obj.addProperty("user_id", 1);
        obj.addProperty("board_id", 1);

        ResultActions actions =
            mvc.perform(
                post("/comment")
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

    @DisplayName("댓글 리스트 가져오기 테스트")
    @Test
    void getAllComment() throws Exception{

        given(commentService.getAllComments(any())).willReturn(comments);

        ResultActions actions =
            mvc.perform(
                get("/comment/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"));
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("success").value(true))
            .andExpect(jsonPath("message").doesNotExist())
            .andDo(print());
    }

    @DisplayName("댓글 삭제 테스트")
    @Test
    void deleteComment() throws Exception{
        ResultActions actions =
            mvc.perform(
                delete("/comment/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"));

        actions.andDo(print());
    }
}