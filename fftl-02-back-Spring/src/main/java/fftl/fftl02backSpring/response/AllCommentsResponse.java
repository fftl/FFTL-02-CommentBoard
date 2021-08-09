package fftl.fftl02backSpring.response;

import fftl.fftl02backSpring.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class AllCommentsResponse {
    private String success;
    private String msg;
    private List<Comment> comments;

    public AllCommentsResponse(String success, String msg, List<Comment> comments) {
        this.success = success;
        this.msg = msg;
        this.comments = comments;
    }
}
