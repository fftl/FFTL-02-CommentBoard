package fftl.fftl02backSpring.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {

    private Long user_id;
    private String username;
    private String nickname;

}
