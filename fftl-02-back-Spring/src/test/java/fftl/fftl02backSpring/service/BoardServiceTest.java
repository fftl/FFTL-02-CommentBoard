package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.request.SaveBoardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;


//    private Board board = new Board(1L, "title", "content", "nickname","2021-12-31", 1L);
    private SaveBoardDto saveBoardDto = new SaveBoardDto("title", "content", "nickname","2021-12-31", 1L);
    private Board board = saveBoardDto.toEntity();

    @Test
    void saveBoard() {

        //given
//        when(boardRepository.save(board)).thenReturn(board);
        doReturn(board).when(boardRepository.save(board));

        //when
        boardService.saveBoard(saveBoardDto);

        //then
        verify(boardRepository).save(board);
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