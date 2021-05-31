package fftl.fftl02backSpring.controller;

import fftl.fftl02backSpring.entity.Comment;
import fftl.fftl02backSpring.request.SaveCommentDto;
import fftl.fftl02backSpring.response.AllCommentsResponse;
import fftl.fftl02backSpring.response.BasicResponse;
import fftl.fftl02backSpring.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value="*")
@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<BasicResponse> addComment(@RequestBody SaveCommentDto saveCommentDto){
        if(!commentService.saveComment(saveCommentDto)){
            throw new RuntimeException();
        }
        return new ResponseEntity<>(new BasicResponse("true", "댓글 작성에 성공하였습니다."), HttpStatus.OK);
    }

    @GetMapping("/{bid}")
    public ResponseEntity<AllCommentsResponse> getAllComment(@PathVariable Long bid){
        List<Comment> comments = commentService.getAllComments(bid);

        return new ResponseEntity<>(new AllCommentsResponse("true", "모든 댓글 가져오기 성공", comments), HttpStatus.OK);
    }

    @DeleteMapping("/{bid}")
    public ResponseEntity<BasicResponse> deleteComment(@PathVariable Long cid){
        commentService.deleteComment(cid);
        return new ResponseEntity<>(new BasicResponse("true", "댓글 삭제하기 성공"), HttpStatus.OK);
    }

}
