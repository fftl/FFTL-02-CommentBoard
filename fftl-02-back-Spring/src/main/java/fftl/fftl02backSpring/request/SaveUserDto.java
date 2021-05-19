package fftl.fftl02backSpring.request;

import fftl.fftl02backSpring.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserDto{

    private String username;
    private String password;
    private String joinDate;
    private String nickname;

    public SaveUserDto(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.joinDate = "test";
        this.nickname = nickname;
    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .joinDate("test")
                .nickname(nickname)
                .build();
    }
}
