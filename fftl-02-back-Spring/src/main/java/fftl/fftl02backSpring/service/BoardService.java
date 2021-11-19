package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.request.SaveBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getAllBoard(){
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public Board getOneBoard(Long board_id){
        Board board = boardRepository.findById(board_id).get();
        return board;
    }

    @Transactional
    public final Board saveBoard(final SaveBoardDto saveBoardDto){
        Board board = saveBoardDto.toEntity();

        return boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(Long board_id, SaveBoardDto saveBoardDto){
        Board board = boardRepository.getOne(board_id);
        board.updateBoard(saveBoardDto);
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long board_id){
        Board board = boardRepository.findById(board_id).get();
        boardRepository.delete(board);
    }


}
