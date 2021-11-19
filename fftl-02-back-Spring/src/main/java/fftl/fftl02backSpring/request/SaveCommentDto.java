package fftl.fftl02backSpring.request;

import fftl.fftl02backSpring.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveCommentDto {

    private String nickname;
    private String comment;
    private String regdate;
    private Long user_id;
    private Long board_id;

    public SaveCommentDto(String nickname, String comment, String regdate) {
        this.nickname = nickname;
        this.comment = comment;
        this.regdate = regdate;
    }

    public Comment toEntity(){
        return Comment.builder()
                .nickname(nickname)
                .comment(comment)
                .regdate(regdate)
                .build();
    }
}
