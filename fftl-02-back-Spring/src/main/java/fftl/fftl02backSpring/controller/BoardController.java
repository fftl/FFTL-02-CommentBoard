package fftl.fftl02backSpring.controller;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.request.SaveBoardDto;
import fftl.fftl02backSpring.response.*;
import fftl.fftl02backSpring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/board")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity getAllBoard(){
        List<Board> boards= boardService.getAllBoard();
        return new ResponseEntity(DefaultResponse.response(StatusCode.OK, ResponseMessage.READ_USER, boards), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BasicResponse> saveBoard(@RequestBody SaveBoardDto saveBoardDto){
        boardService.saveBoard(saveBoardDto);
        return new ResponseEntity<>(new BasicResponse("true", "게시글 등록 성공"), HttpStatus.OK);
    }

    @GetMapping("/{board_id}")
    public ResponseEntity<OneBoardResponse> getOneBoard(@PathVariable Long board_id){
        Board board = boardService.getOneBoard(board_id);
        return new ResponseEntity<>(new OneBoardResponse("true", "게시글 조회 성공", board), HttpStatus.OK);
    }

    @PatchMapping("/{board_id}")
    public ResponseEntity<BasicResponse> updateBoard(@RequestBody SaveBoardDto saveBoardDto, @PathVariable Long board_id){
        boardService.updateBoard(board_id, saveBoardDto);
        return new ResponseEntity<>(new BasicResponse("true", "게시글 수정 성공"), HttpStatus.OK);
    }

    @DeleteMapping("/{board_id}")
    public ResponseEntity<BasicResponse> deleteBoard(@PathVariable Long board_id){
        boardService.deleteBoard(board_id);
        return new ResponseEntity<>(new BasicResponse("true", "게시글 삭제 성공"), HttpStatus.OK);
    }
}
