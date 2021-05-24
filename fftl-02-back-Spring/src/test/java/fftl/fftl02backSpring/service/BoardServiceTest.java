package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.request.SaveBoardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @Test
    void saveBoard() {
        String title = "제목입니다.";
        String nickname = "작성자입니다.";
        String content = "내용입니다.";
        String bregdate = "2021-12-31";
        Long uid = 1L;

        List<Board> boards1 = boardService.getAllBoard();
        int before = boards1.size();

        //given
        boardService.saveBoard(new SaveBoardDto(title, nickname, content, bregdate, uid));
        
        //when
        List<Board> boards2 = boardService.getAllBoard();
        int after = boards2.size();

        //then
        assertTrue(before == 0);
        assertTrue(after == 1);
    }
    
    @Test
    void getAllBoard() {
    }

    @Test
    void getOneBoard() {
    }



    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }
}