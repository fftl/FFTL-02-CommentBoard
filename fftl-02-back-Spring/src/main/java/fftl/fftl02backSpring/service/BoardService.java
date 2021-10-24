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

    public Board getOneBoard(Long bid){
        Board board = boardRepository.findById(bid).get();
        return board;
    }

    @Transactional
    public final Board saveBoard(final SaveBoardDto saveBoardDto){
        Board board = saveBoardDto.toEntity();

        return boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(Long bid, SaveBoardDto saveBoardDto){
        Board board = boardRepository.getOne(bid);
        board.updateBoard(saveBoardDto);
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long bid){
        Board board = boardRepository.findById(bid).get();
        boardRepository.delete(board);
    }


}
