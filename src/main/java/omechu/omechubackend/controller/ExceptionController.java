package omechu.omechubackend.controller;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.exception.InvalidRequest;
import omechu.omechubackend.exception.OmechuException;
import omechu.omechubackend.exception.PostNotFound;
import omechu.omechubackend.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    /**
     * Spring에서 제공하는 exception은 따로 관리
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        System.out.println("============================");
        Map<String, String> validationInitialValue = new HashMap<>();
        // MethodArgumentNotValidException
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .validation(validationInitialValue)
                .build();

        for (FieldError fieldError : e.getFieldErrors() ) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public ErrorResponse expiredJwtExceptionHandler(ExpiredJwtException e) {
        Map<String, String> validationInitialValue = new HashMap<>();
        // MethodArgumentNotValidException
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .validation(validationInitialValue)
                .build();


       /* for (FieldError fieldError : e.getFieldErrors() ) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }*/

        return response;
    }

    /**
     * 에러가 계속 늘어날 수 있기 때문에 최상위 에러 클래스를 만들어 관리
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(OmechuException.class)
    public ResponseEntity<ErrorResponse> omechuException(OmechuException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();


        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
                .body(body);

        return response;
    }
}
