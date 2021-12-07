package fftl.fftl02backSpring.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDto {

    private Long id;
    private String comment;
    private String nickname;
    private String regdate;
    private Long user_id;

}
