package com.ken.taroserver.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ken.tarocommon.result.Result;
import com.ken.taropojo.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex, WebRequest request) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setError("Internal Server Error");
        errorDto.setMessage(ex.getMessage());
        errorDto.setPath(request.getContextPath()); // 注意：getRequestURI() 不可直接在 WebRequest 使用，改为 getContextPath()
        
        // 记录日志
        logger.error("Unhandled exception occurred at {}: {}", request.getContextPath(), ex.toString(), ex);
        return Result.error(errorDto);
    }
}
