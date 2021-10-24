package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.request.SaveBoardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @Test
    void saveBoard() {

        //given
        SaveBoardDto saveBoardDto = new SaveBoardDto("title", "content", "nickname", "2021-03-01", 1L);
        given(boardRepository.save(any())).willReturn(saveBoardDto.toEntity());

        //when
        Board board = boardService.saveBoard(saveBoardDto);


        //then
        assertThat(board.getNickname()).isEqualTo("nickname");
    }

    @Test
    void getAllBoard() {

        //given
        List<Board> boards = new ArrayList<>();
        Board board1 = Board.builder().title("제목!").bregdate("2021-03-01").content("내용입니다!").nickname("작성자에요!").bid(1L).uid(1L).build();
        boards.add(board1);
        given(boardRepository.findAll()).willReturn(boards);

        //when
        List<Board> findAll = boardService.getAllBoard();

        //then
        assertThat(findAll.get(0).getNickname()).isEqualTo(board1.getNickname());

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