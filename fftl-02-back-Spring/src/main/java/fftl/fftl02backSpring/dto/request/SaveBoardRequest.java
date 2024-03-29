package fftl.fftl02backSpring.dto.request;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.entity.User;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveBoardRequest {

    private String title;
    private String content;
    private String nickname;
    private String regdate;
    private Long user_id;
    private User user;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .nickname(nickname)
                .regdate(regdate)
                .user(user)
                .build();
    }
}
