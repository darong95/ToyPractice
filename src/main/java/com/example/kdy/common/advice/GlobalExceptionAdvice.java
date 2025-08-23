package com.example.kdy.common.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException runtimeException, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("resultMessage", runtimeException.getMessage());
        return "redirect:/home";  // 실패해도 지정한 페이지로 이동
    }
}
