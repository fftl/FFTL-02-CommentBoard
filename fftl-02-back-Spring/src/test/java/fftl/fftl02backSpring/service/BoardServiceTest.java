package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.request.SaveBoardDto;
<<<<<<< HEAD
<<<<<<< HEAD
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
=======
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
=======
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
<<<<<<< HEAD
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
=======
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

<<<<<<< HEAD
<<<<<<< HEAD
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
        board1 = Board.builder().title("제목!").bregdate("2021-03-01").content("내용입니다!").nickname("작성자에요!").build();
        board2 = Board.builder().title("제목222").bregdate("2021-03-02").content("내용입니다!").nickname("작성자22!").build();

        boards.add(board1);
        boards.add(board2);
=======
=======
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study
    //1.
    private BoardRepository boardRepository = Mockito.mock(BoardRepository.class);
    private BoardService boardService;

    @BeforeEach
    public void setUp(){
        boardService = new BoardService(boardRepository);
<<<<<<< HEAD
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study
=======
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study
    }

    @Test
    void saveBoard() {

        //given
        SaveBoardDto saveBoardDto = new SaveBoardDto("title", "content", "nickname", "2021-03-01", 1L);
<<<<<<< HEAD
<<<<<<< HEAD
        given(boardRepository.save(any())).willReturn(saveBoardDto.toEntity());
=======
        //2.
        when(boardRepository.save(any(Board.class))).then(returnsFirstArg());
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study
=======
        //2.
        when(boardRepository.save(any(Board.class))).then(returnsFirstArg());
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study

        //when
        Board board = boardService.saveBoard(saveBoardDto);

        //then
<<<<<<< HEAD
<<<<<<< HEAD
=======
        //3.
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study
=======
        //3.
>>>>>>> parent of 2cbe73d... 211018,2329, testing code study
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