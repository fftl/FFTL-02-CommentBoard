package fftl.fftl02backSpring.controller;

import fftl.fftl02backSpring.dto.BoardDto;
import fftl.fftl02backSpring.dto.response.Response;
import fftl.fftl02backSpring.entity.Board;
import fftl.fftl02backSpring.dto.request.SaveBoardRequest;
import fftl.fftl02backSpring.response.*;
import fftl.fftl02backSpring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/board")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public Response getAllBoard(){
        List<Board> boards= boardService.getAllBoard();
        BoardDto[] boardDtos = new BoardDto[boards.size()];

        for(int i=0; i<boards.size(); i++){
            boardDtos[i] = BoardDto.builder()
                    .id(boards.get(i).getId())
                    .title(boards.get(i).getTitle())
                    .content(boards.get(i).getContent())
                    .nickname(boards.get(i).getNickname())
                    .regdate(boards.get(i).getRegdate())
                    .build();
        }

        return new Response(true, null, boardDtos);
    }

    @PostMapping("")
    public Response saveBoard(@RequestBody SaveBoardRequest saveBoardRequest){
        Board board = boardService.saveBoard(saveBoardRequest);
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .nickname(board.getNickname())
                .regdate(board.getRegdate())
                .build();

        return new Response(true, null, boardDto);
    }

    @PatchMapping("/{board_id}")
    public Response updateBoard(@RequestBody SaveBoardRequest saveBoardRequest, @PathVariable Long board_id){
        Board board = boardService.updateBoard(board_id, saveBoardRequest);
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .nickname(board.getNickname())
                .regdate(board.getRegdate())
                .build();

        return new Response(true, null, boardDto);
    }

    @DeleteMapping("/{board_id}")
    public Response deleteBoard(@PathVariable Long board_id){
        boardService.deleteBoard(board_id);
        return new Response(true, null);
    }

    @GetMapping("/{board_id}")
    public Response getOneBoard(@PathVariable Long board_id){
        Board board = boardService.getOneBoard(board_id);
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .nickname(board.getNickname())
                .regdate(board.getRegdate())
                .build();

        return new Response(true, null, boardDto);
    }

}
