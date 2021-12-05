package fftl.fftl02backSpring.request;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.entity.Comment;
import fftl.fftl02backSpring.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveCommentDto {

    private String nickname;
    private String comment;
    private String regdate;
    private Long user_id;
    private Long board_id;
    private User user;
    private Board board;


    public Comment toEntity(){
        return Comment.builder()
                .nickname(nickname)
                .comment(comment)
                .regdate(regdate)
                .board(board)
                .user(user)
                .build();
    }
}
