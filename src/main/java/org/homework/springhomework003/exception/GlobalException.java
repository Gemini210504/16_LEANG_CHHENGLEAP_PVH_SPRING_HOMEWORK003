package org.homework.springhomework003.exception;


import org.homework.springhomework003.model.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongInputException.class)
    public ResponseEntity<?> handleWrongInputException(WrongInputException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateException(DuplicateException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

}
