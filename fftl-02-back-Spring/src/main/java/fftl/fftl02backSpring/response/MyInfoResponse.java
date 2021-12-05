package fftl.fftl02backSpring.response;

import fftl.fftl02backSpring.entity.User;
import lombok.Getter;

@Getter
public class MyInfoResponse {

    private String success;
    private String msg;
    private Long user_id;
    private String username;
    private String nickname;

    public MyInfoResponse(String success, String msg, Long user_id, String username, String nickname) {
        this.user_id = user_id;
        this.success = success;
        this.msg = msg;
        this.username = username;
        this.nickname = nickname;
    }
}
