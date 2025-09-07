package com.example.kdy.common.advice;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice(basePackages = {
        "com.example.kdy.auth.controller.web",
        "com.example.kdy.board.controller.web"
})
public class GlobalExceptionAdvice {
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException runtimeException, RedirectAttributes redirectAttributes) {
        log.info("[WEB ERROR] IllegalArgumentException 발생!");
        redirectAttributes.addFlashAttribute("resultMessage", runtimeException.getMessage());

        return "redirect:/home";  // 실패해도 지정한 페이지로 이동
    }
}
