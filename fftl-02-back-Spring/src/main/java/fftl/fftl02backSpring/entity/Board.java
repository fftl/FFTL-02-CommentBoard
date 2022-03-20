package fftl.fftl02backSpring.entity;

import fftl.fftl02backSpring.request.SaveBoardRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Board {

    @Column(name = "board_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "board_title")
    private String title;

    @Column(name = "board_content")
    private String content;

    @Column(name = "board_nickname")
    private String nickname;

    @Column(name = "board_regdate")
    private String regdate;

    @ManyToOne
    @JoinColumn(name="users_id")
    private User user;

    @OneToMany(mappedBy = "board")
    List<Comment> comments = new ArrayList<>();

    public void updateBoard(SaveBoardRequest saveBoardRequest){
        this.title = saveBoardRequest.getTitle();
        this.content = saveBoardRequest.getContent();
    }
}
