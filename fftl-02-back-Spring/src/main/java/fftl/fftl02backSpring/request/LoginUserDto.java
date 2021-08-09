package fftl.fftl02backSpring.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserDto {

    private String username;
    private String password;

    public LoginUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
