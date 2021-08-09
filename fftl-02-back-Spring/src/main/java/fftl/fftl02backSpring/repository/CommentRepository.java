package fftl.fftl02backSpring.repository;

import fftl.fftl02backSpring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBid(Long bid);
}
