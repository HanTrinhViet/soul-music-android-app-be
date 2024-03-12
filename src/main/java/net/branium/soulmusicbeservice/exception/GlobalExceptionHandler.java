package net.branium.soulmusicbeservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import net.branium.soulmusicbeservice.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleGenericException(HttpServletRequest http, Exception ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(http.getServletPath())
                .errors(List.of(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()))
                .build();
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .path(((ServletWebRequest) request).getRequest().getServletPath())
                .errors(ex.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(errorDTO, headers, status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(HttpServletRequest httpServletRequest, UserNotFoundException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .path(httpServletRequest.getServletPath())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlaylistNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlePlaylistNotFoundException(HttpServletRequest httpServletRequest, UserNotFoundException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .path(httpServletRequest.getServletPath())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
