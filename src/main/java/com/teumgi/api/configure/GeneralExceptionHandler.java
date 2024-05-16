package com.teumgi.api.configure;

import com.teumgi.api.dto.ApiResult;
import com.teumgi.api.error.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GeneralExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ResponseEntity<ApiResult<?>> newResponse(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ApiResult.ERROR(throwable, status), headers, status);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return newResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
        IllegalStateException.class, IllegalArgumentException.class,
        TypeMismatchException.class, HttpMessageNotReadableException.class,
        MissingServletRequestParameterException.class, MultipartException.class,
    })
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<?> handleHttpMediaTypeException(Exception e) {
        return newResponse(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(Exception e) {
        return newResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public ResponseEntity<?> handleServiceRuntimeException(ServiceRuntimeException e) {
        if (e instanceof NotFoundException) {
            return newResponse(e, HttpStatus.NOT_FOUND);
        }
        if (e instanceof UnauthorizedException) {
            return newResponse(e, HttpStatus.UNAUTHORIZED);
        }
        if (e instanceof NotImageExtension) {
            return newResponse(e, HttpStatus.BAD_REQUEST);
        }
        if (e instanceof NotOwnerException) {
            return newResponse(e, HttpStatus.FORBIDDEN);
        }

        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        if (e instanceof AccessDeniedException) {
            return newResponse(e, HttpStatus.FORBIDDEN);
        }
        log.error("예기치 않은 예외 발생: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}