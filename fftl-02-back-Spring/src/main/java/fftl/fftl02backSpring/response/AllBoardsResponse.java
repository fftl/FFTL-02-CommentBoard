package fftl.fftl02backSpring.response;

import fftl.fftl02backSpring.entity.Board;
import lombok.Getter;

import java.util.List;

@Getter
public class AllBoardsResponse {

    private String success;
    private String msg;
    private List<Board> boards;

    public AllBoardsResponse(String success, String msg, List<Board> boards) {
        this.success = success;
        this.msg = msg;
        this.boards = boards;
    }
}
