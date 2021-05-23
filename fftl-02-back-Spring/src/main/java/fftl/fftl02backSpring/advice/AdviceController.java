package fftl.fftl02backSpring.advice;

import fftl.fftl02backSpring.response.FailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@ControllerAdvice
@RestController
public class AdviceController {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<FailResponse> basicException(HttpServletRequest request, Exception e){
        return new ResponseEntity<>(new FailResponse("fail", "알 수 없는 오류입니다."), HttpStatus.OK);
    }

}
