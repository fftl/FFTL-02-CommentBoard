package fftl.fftl02backSpring.controller;

import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.response.AllBoardsResponse;
import fftl.fftl02backSpring.response.BasicResponse;
import fftl.fftl02backSpring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/board")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity<AllBoardsResponse> getAllBoard(){
        List<Board> boards= boardService.getAllBoard();

        return new ResponseEntity<>(new AllBoardsResponse("true", "게시글들 가져오기 성공", boards), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BasicResponse> addBoard(){

        return new ResponseEntity<>(new BasicResponse("true", "게시글 등록 성공"), HttpStatus.OK);
    }

    @PostMapping("c")
    public String addBoard(){
        return "";
    }

    @PutMapping("d")
    public String updateBoard(){
        return "";
    }
}
