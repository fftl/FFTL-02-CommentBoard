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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String nickname;
    private String regdate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    List<Comment> comments = new ArrayList<>();

    public void updateBoard(SaveBoardRequest saveBoardRequest){
        this.title = saveBoardRequest.getTitle();
        this.content = saveBoardRequest.getContent();
    }
}
