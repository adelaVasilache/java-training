package javatraining.training.controllers.advice;

import io.jsonwebtoken.ExpiredJwtException;
import javatraining.training.dtos.ErrorDto;
import javatraining.training.exceptions.DuplicatePostException;
import javatraining.training.exceptions.DuplicateUserException;
import javatraining.training.exceptions.GradeException;
import javatraining.training.exceptions.InvalidDataException;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.exceptions.UserRightsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adela Vasilache on 25.04.2018
 */
@ControllerAdvice
public class CustomControllerAdvice {
    private final MessageSource messageSource;

    @Autowired
    public CustomControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleNotFoundException(Exception e) {
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.NOT_FOUND).
                message(messageSource.getMessage("error.not.found", null, null)).build();

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateUserException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDto> handleDuplicateUserException(Exception e) {
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.CONFLICT).
                message(messageSource.getMessage("error.user.exists", null, null)).build();

        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {UserRightsException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorDto> handleUserRightsException(Exception e) {
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.FORBIDDEN).
                message(messageSource.getMessage("error.user.rights", null, null)).build();

        return new ResponseEntity<>(errorDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {DuplicatePostException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDto> handleDuplicatePostException(Exception e) {
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.CONFLICT).
                message(messageSource.getMessage("error.duplicate.post", null, null)).build();

        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {InvalidDataException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleInvalidDataException(Exception e) {
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.BAD_REQUEST).
                message(messageSource.getMessage("error.invalid.data", null, null)).build();

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {GradeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleGradeException(Exception e) {
        ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.BAD_REQUEST).
                message(messageSource.getMessage("error.invalid.grade", null, null)).build();

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
