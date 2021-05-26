package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.request.SaveBoardDto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BoardServiceTest {

    //1.
    private BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private BoardService boardService;

    @BeforeEach
    public void setUp(){
        boardService = new BoardService(boardRepository);
    }

    @Test
    void saveBoard() {
        SaveBoardDto saveBoardDto = new SaveBoardDto("title", "content", "nickname", "2021-03-01", 1L);
        //2.
        when(boardRepository.save(any(Board.class))).then(returnsFirstArg());
        Board board = boardService.saveBoard(saveBoardDto);
        //3.
        assertThat(board.getNickname()).isEqualTo("nickname");
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