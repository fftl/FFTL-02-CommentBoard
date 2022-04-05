package fftl.fftl02backSpring.controller;

import fftl.fftl02backSpring.dto.CommentDto;
import fftl.fftl02backSpring.entity.Comment;
import fftl.fftl02backSpring.dto.request.SaveCommentRequest;
import fftl.fftl02backSpring.dto.response.Response;
import fftl.fftl02backSpring.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public Response addComment(@RequestBody SaveCommentRequest saveCommentRequest){
        Comment comment = commentService.saveComment(saveCommentRequest);
        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .nickname(comment.getNickname())
                .regdate(comment.getRegdate())
                .user_id(comment.getUser().getId())
                .build();

        return new Response(true, null, commentDto);
    }

    @GetMapping("/{board_id}")
    public Response getAllComment(@PathVariable Long board_id){
        List<Comment> comments = commentService.getAllComments(board_id);
        CommentDto[] commentDtos = new CommentDto[comments.size()];
        for(int i=0; i<comments.size(); i++){
            commentDtos[i] = CommentDto.builder()
                    .id(comments.get(i).getId())
                    .comment(comments.get(i).getComment())
                    .nickname(comments.get(i).getNickname())
                    .regdate(comments.get(i).getRegdate())
                    .user_id(comments.get(i).getUser().getId())
                    .build();

        }

        return new Response(true, null, commentDtos);
    }

    @DeleteMapping("/{comment_id}")
    public Response deleteComment(@PathVariable Long comment_id){
        commentService.deleteComment(comment_id);
        return new Response(true, null, true);
    }

}
