package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.entity.Comment;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.repository.CommentRepository;
import fftl.fftl02backSpring.repository.UserRepository;
import fftl.fftl02backSpring.request.SaveCommentDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public boolean saveComment(SaveCommentDto saveCommentDto){

        saveCommentDto.setBoard(boardRepository.findById(saveCommentDto.getBoard_id()).orElse(null));
        saveCommentDto.setUser(userRepository.findById(saveCommentDto.getUser_id()).orElse(null));
        Comment comment = commentRepository.save(saveCommentDto.toEntity());

        if(comment != null){
            return true;
        }

        return false;
    }

    public List<Comment> getAllComments(Long board_id){
        Board board = boardRepository.findById(board_id).get();
        return board.getComments();
    }

    @Transactional
    public void deleteComment(Long comment_id){
        Comment comment = commentRepository.findById(comment_id).get();
        commentRepository.delete(comment);
    }
}
