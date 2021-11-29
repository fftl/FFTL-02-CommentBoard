package fftl.fftl02backSpring.repository;

import fftl.fftl02backSpring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
