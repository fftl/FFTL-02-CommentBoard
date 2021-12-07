package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.repository.UserRepository;
import fftl.fftl02backSpring.request.SaveBoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final UserRepository userRepository;
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
    public Board saveBoard(SaveBoardRequest saveBoardRequest){
        saveBoardRequest.setUser(userRepository.findById(saveBoardRequest.getUser_id()).orElse(null));
        Board board = saveBoardRequest.toEntity();
        return boardRepository.save(board);
    }

    @Transactional
    public Board updateBoard(Long board_id, SaveBoardRequest saveBoardRequest){
        Board board = boardRepository.getOne(board_id);
        board.updateBoard(saveBoardRequest);
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long board_id){
        Board board = boardRepository.findById(board_id).get();
        boardRepository.delete(board);
    }


}
