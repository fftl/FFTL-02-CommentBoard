package fftl.fftl02backSpring.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String success;
    private String access_token;

    public LoginResponse(String success, String access_token) {
        this.success = success;
        this.access_token = access_token;
    }
}
