package fftl.fftl02backSpring.entity;

import fftl.fftl02backSpring.request.SaveBoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bid;

    private String title;
    private String content;
    private String nickname;
    private String bregdate;

    private Long uid;

    public void updateBoard(SaveBoardDto saveBoardDto){
        this.title = saveBoardDto.getTitle();
        this.content = saveBoardDto.getContent();
    }
}
