package shop.mtcoding.blog2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blog2.Util.Script;
import shop.mtcoding.blog2.dto.ResponseDto;

@RestControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e){
        return new ResponseEntity<>(Script.back(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> customApiException(CustomApiException e){
        return new ResponseEntity<>(new ResponseDto<>(-1,e.getMessage(),null), e.getStatus());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> customException(BindException e){
        return new ResponseEntity<>(Script.back("필수값을 입력하세요"), HttpStatus.BAD_REQUEST);
    }

}
