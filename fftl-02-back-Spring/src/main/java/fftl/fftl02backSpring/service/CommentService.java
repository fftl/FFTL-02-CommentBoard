package fftl.fftl02backSpring.service;

import fftl.fftl02backSpring.entity.Comment;
import fftl.fftl02backSpring.repository.CommentRepository;
import fftl.fftl02backSpring.request.SaveCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public boolean saveComment(SaveCommentDto saveCommentDto){
        Comment comment = commentRepository.save(saveCommentDto.toEntity());

        if(comment != null){
            return true;
        }
        return false;
    }

    public List<Comment> getAllComments(Long board_id){
        List<Comment> comments = commentRepository.findByBid(board_id);
        return comments;
    }

    @Transactional
    public void deleteComment(Long cid){
        Comment comment = commentRepository.findById(cid).get();
        commentRepository.delete(comment);
    }
}
