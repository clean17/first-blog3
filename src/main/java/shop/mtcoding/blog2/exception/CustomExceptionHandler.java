package shop.mtcoding.blog2.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @ExceptionHandler(BindException.class) // @NotBlack가 발생시킨 익셉션 핸들링
    public ResponseEntity<?> customException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        // 첫 번째 필드 에러의 메시지 가져오기
        String errorMessage = fieldErrors.get(0).getDefaultMessage();
        return new ResponseEntity<>(Script.back(errorMessage), HttpStatus.BAD_REQUEST);
    }

}
