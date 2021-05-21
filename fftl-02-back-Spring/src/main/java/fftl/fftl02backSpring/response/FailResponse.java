package fftl.fftl02backSpring.response;

import lombok.Getter;

@Getter
public class FailResponse {

    private String success;
    private String msg;

    public FailResponse(String success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
