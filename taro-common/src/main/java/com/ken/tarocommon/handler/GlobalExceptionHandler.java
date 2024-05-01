//package com.ken.tarocommon.handler;
//
//import com.ken.tarocommon.exception.AccountNotFoundException;
//import com.ken.tarocommon.exception.PasswordErrorException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(AccountNotFoundException.class)
//    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
//
//    @ExceptionHandler(PasswordErrorException.class)
//    public ResponseEntity<Object> handlePasswordErrorException(PasswordErrorException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
//    }
//    // 其他异常处理
//}

