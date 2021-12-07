package fftl.fftl02backSpring.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
    private String regdate;

}
