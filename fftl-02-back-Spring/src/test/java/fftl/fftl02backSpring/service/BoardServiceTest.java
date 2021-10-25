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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    static List<Board> boards;
    static Board board1;
    static Board board2;

    @BeforeEach
    void init() {
        boards = new ArrayList<>();
        board1 = Board.builder().title("제목!").bregdate("2021-03-01").content("내용입니다!").nickname("작성자에요!").bid(1L).uid(1L).build();
        board2 = Board.builder().title("제목222").bregdate("2021-03-02").content("내용입니다!").nickname("작성자22!").bid(2L).uid(2L).build();

        boards.add(board1);
        boards.add(board2);
    }

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
        given(boardRepository.findAll()).willReturn(boards);

        //when
        List<Board> findAll = boardService.getAllBoard();

        //then
        assertThat(findAll.size()).isEqualTo(boards.size());
        assertThat(findAll.get(0).getNickname()).isEqualTo(board1.getNickname());
        assertThat(findAll.get(1).getNickname()).isEqualTo(board2.getNickname());

    }

    @Test
    void getOneBoard() {

        //given
        given(boardRepository.findById(any())).willReturn(Optional.ofNullable(board1));

        //when
        Board board = boardService.getOneBoard(1L);

        //then
        assertThat(board.getNickname()).isEqualTo(board1.getNickname());


    }



    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }
}