package fftl.fftl02backSpring.response;

import fftl.fftl02backSpring.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
public class OneBoardResponse {

    private String success;
    private String msg;
    private Board board;

    public OneBoardResponse(String success, String msg, Board board) {
        this.success = success;
        this.msg = msg;
        this.board = board;
    }
}
