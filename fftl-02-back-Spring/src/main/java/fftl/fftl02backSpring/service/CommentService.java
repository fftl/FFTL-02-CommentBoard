package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.entity.Comment;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.repository.CommentRepository;
import fftl.fftl02backSpring.repository.UserRepository;
import fftl.fftl02backSpring.dto.request.SaveCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Comment saveComment(SaveCommentRequest saveCommentRequest){

        saveCommentRequest.setBoard(boardRepository.findById(saveCommentRequest.getBoard_id()).orElse(null));
        saveCommentRequest.setUser(userRepository.findById(saveCommentRequest.getUser_id()).orElse(null));
        Comment comment = commentRepository.save(saveCommentRequest.toEntity());

        return comment;
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
