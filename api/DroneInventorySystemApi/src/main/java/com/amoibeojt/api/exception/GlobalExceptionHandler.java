package com.amoibeojt.api.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amoibeojt.api.dto.ErrorResponseDTO;

/**
 * グローバル例外ハンドラ
 * 
 * @author your name
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
            "error",
            "予期しないエラーが発生しました",
            "INTERNAL_ERROR",
            ex.getMessage(),
            ZonedDateTime.now().toString()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidInput(InvalidInputException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
            "error",
            "入力が無効です",
            "INVALID_REQUEST",
            ex.getMessage(),
            ZonedDateTime.now().toString()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}