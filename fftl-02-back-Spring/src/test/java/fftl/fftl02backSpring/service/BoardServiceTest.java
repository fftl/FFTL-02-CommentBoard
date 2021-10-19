package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.request.SaveBoardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    private BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private BoardService boardService;

    @BeforeEach
    void init() {
        boardService = new BoardService(boardRepository);
    }

     @Test
    void saveBoard() {
        SaveBoardDto saveBoardDto = new SaveBoardDto("title", "content", "nickname", "2021-03-01", 1L);

//        //2.
//        when(boardRepository.save(any(Board.class))).then(returnsFirstArg());
//        Board board = boardService.saveBoard(saveBoardDto);
//
//        //3.
//        assertThat(board.getNickname()), is(equalTo("nickname"));
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