package dev.momo.api.global.exception;

import dev.momo.api.global.response.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class BaseControllerAdvice {

    @ExceptionHandler(SearchResultNotFoundException.class)
    public BaseResponse<SearchResultNotFoundException>
    test(HttpServletRequest request, HttpServletResponse response, SearchResultNotFoundException e) {
        return new BaseResponse<SearchResultNotFoundException>(e.getStatus());
    }

}
