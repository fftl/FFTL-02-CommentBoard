package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.entity.Comment;
import fftl.fftl02backSpring.repository.BoardRepository;
import fftl.fftl02backSpring.repository.CommentRepository;
import fftl.fftl02backSpring.request.SaveCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public boolean saveComment(SaveCommentDto saveCommentDto){
        Comment comment = commentRepository.save(saveCommentDto.toEntity());

        if(comment != null){
            return true;
        }
        return false;
    }

    public List<Comment> getAllComments(Long board_id){
        Optional<Board> byId = boardRepository.findById(board_id);
        return byId.get().getComments();
    }

    @Transactional
    public void deleteComment(Long comment_id){
        Comment comment = commentRepository.findById(comment_id).get();
        commentRepository.delete(comment);
    }
}
