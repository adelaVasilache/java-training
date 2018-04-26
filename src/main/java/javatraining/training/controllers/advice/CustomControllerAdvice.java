package javatraining.training.controllers.advice;

import javatraining.training.dtos.ErrorDto;
import javatraining.training.exceptions.DuplicateUserException;
import javatraining.training.exceptions.NotFoundException;
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
    public ResponseEntity<String> handleDuplicateUserException(Exception e) {

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
