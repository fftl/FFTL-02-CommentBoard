package fftl.fftl02backSpring.response;

import lombok.Getter;

@Getter
public class BasicResponse {

    private String success;
    private String msg;

    public BasicResponse(String success, String msg){
        this.success = success;
        this.msg = msg;
    }
}
