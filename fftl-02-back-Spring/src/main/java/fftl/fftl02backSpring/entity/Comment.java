package fftl.fftl02backSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @Column(name = "comment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_nickname")
    private String nickname;

    @Column(name = "comment_comment")
    private String comment;

    @Column(name = "comment_regdate")
    private String regdate;

    @ManyToOne
    @JoinColumn(name="users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;
}
