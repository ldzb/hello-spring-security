package kr.ac.hansung.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, RedirectAttributes ra) {
        ra.addFlashAttribute("errorMessage", "수정 권한이 없습니다. 관리자 계정으로 로그인하세요.");
        return "redirect:/products";
    }
}
