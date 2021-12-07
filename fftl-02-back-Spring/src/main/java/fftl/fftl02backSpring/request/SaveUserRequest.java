package fftl.fftl02backSpring.request;

import fftl.fftl02backSpring.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequest {

    private String username;
    private String password;
    private String nickname;
    private String regdate;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .joinDate(regdate)
                .build();
    }
}
