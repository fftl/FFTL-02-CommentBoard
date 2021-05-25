package fftl.fftl02backSpring.request;

import fftl.fftl02backSpring.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveBoardDto {

    private String title;
    private String nickname;
    private String content;
    private String bregdate;
    private Long uid;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .nickname(nickname)
                .content(content)
                .bregdate(bregdate)
                .uid(uid)
                .build();
    }
}
