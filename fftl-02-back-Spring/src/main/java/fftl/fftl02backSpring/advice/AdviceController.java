package fftl.fftl02backSpring.advice;

import fftl.fftl02backSpring.advice.exciptions.BadRequest;
import fftl.fftl02backSpring.advice.exciptions.SameDataExists;
import fftl.fftl02backSpring.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(BadRequest.class)
    protected Response BadRequest(BadRequest ex){
        return new Response(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    protected Response basicException(){
        return new Response(false, "알수없는 오류입니다.", HttpStatus.BAD_REQUEST);
    }

}
