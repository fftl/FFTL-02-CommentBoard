package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.request.SaveBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getAllBoard(){
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public void saveBoard(SaveBoardDto saveBoardDto){
        boardRepository.save(saveBoardDto.toEntity());
    }

    public Board getOneBoard(Long bid){
        Board board = boardRepository.findById(bid).orElseThrow();
        return board;
    }
}
