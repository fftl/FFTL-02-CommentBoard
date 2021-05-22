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
    private String cregdate;

    private Long bid;
    private Long uid;

    public SaveCommentDto(String nickname, String comment, String cregdate, Long bid, Long uid) {
        this.nickname = nickname;
        this.comment = comment;
        this.cregdate = cregdate;
        this.bid = bid;
        this.uid = uid;
    }

    public Comment toEntity(){
        return Comment.builder()
                .nickname(nickname)
                .comment(comment)
                .cregdate(cregdate)
                .uid(uid)
                .bid(bid)
                .build();
    }
}
