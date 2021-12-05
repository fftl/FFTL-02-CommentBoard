package fftl.fftl02backSpring.advice;

import fftl.fftl02backSpring.advice.errors.SameDataExists;
import fftl.fftl02backSpring.response.DefaultResponse;
import fftl.fftl02backSpring.response.ResponseMessage;
import fftl.fftl02backSpring.response.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(SameDataExists.class)
    protected ResponseEntity SameUsernameExists(){
        return new ResponseEntity(DefaultResponse.response(StatusCode.BAD_REQUEST, ResponseMessage.SAME_ID), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity basicException(){
        return new ResponseEntity(new FailResponse("fail", "알 수 없는 오류입니다."), HttpStatus.OK);
    }

}
