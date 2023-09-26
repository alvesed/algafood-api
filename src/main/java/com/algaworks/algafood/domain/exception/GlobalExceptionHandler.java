package com.algaworks.algafood.domain.exception;

import com.ctc.wstx.util.ExceptionUtil;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(
            EntidadeEmUsoException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        body = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(status.getReasonPhrase() + "/" + ex.getMessage()).build();

        return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ex.getRootCause();
        Object body;
        if (rootCause instanceof PropertyBindingException) {
            body = Problema.builder()
                    .dataHora(LocalDateTime.now())
                    .mensagem("PropertyBindingException: " + status.getReasonPhrase() + "/" + ex.getMessage()).build();

            return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
        } else if (rootCause instanceof UnrecognizedPropertyException) {
            body = Problema.builder()
                    .dataHora(LocalDateTime.now())
                    .mensagem("UnrecognizedPropertyException: " + status.getReasonPhrase() + "/" + ex.getMessage()).build();

            return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
        }

        body = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(status.getReasonPhrase() + "/" + ex.getMessage()).build();

        return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problema.Field> problemFields = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                    return Problema.Field.builder()
                            .name(fieldError.getField())
                            //.userMessage(fieldError.getDefaultMessage())
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Object body = Problema.builder()
                .dataHora(LocalDateTime.now())
                .fields(problemFields)
                .mensagem("MethodArgumentNotValid: " + status.getReasonPhrase() + "/" + ex.getMessage()).build();

        return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }
}
