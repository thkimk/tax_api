package com.example.apiserver.advice;

import com.example.apiserver.model.response.ApiDataResult;
import com.example.apiserver.advice.exception.AuthenticationEntryPointException;
import com.example.apiserver.advice.exception.EmailSigninFailedException;
import com.example.apiserver.advice.exception.UserNotFoundException;
import com.example.apiserver.model.response.CommonResult;
import com.example.apiserver.service.ResponseService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice // 예외 발생 시 json 형태로 결과 반환 | 프로젝트의 모든 Controller에 로직 적용
// @RestControllerAdvice(basePackages = "com.example.api_server") : api_server 하위의 Controller 에만 로직 적용
public class ExceptionAdvice {

    private final ResponseService responseService; // 결과에 대한 정보를 도출하는 클래스 명시
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiDataResult defaultException(HttpServletRequest request, Exception e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.failResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
        // 예외 처리 메시지를 MessageSource에서 가져오도록 수정, exception_ko.yml 파일에서 가져온 것임
        // getFailResult : setSuccess, setCode, setMsg
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiDataResult userNotFoundException(HttpServletRequest request, UserNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.failResult(Integer.valueOf(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
        // 예외 처리 메시지를 MessageSource에서 가져오도록 수정
        // getFailResult : setSuccess, setCode, setMsg
    }

    @ExceptionHandler(EmailSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiDataResult emailSigninFailed(HttpServletRequest request, EmailSigninFailedException e) {
        return responseService.failResult(Integer.valueOf(getMessage("emailSigninFailed.code")), getMessage("emailSigninFailed.msg"));
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    public ApiDataResult authenticationEntryPointException(HttpServletRequest request, AuthenticationEntryPointException e) {
        return responseService.failResult(Integer.valueOf(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ApiDataResult AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.failResult((Integer.valueOf(getMessage("accessDenied.code"))), getMessage("accessDenied.msg"));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiDataResult NotFoundException(HttpServletRequest request, Exception e) {
        return responseService.failResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    protected ApiDataResult HttpRequestMethodNotSupportedException(HttpServletRequest request, Exception e) {
        return responseService.failResult(Integer.valueOf(getMessage("notImplemented.code")), getMessage("notImplemented.msg"), e);
    }

    // code 정보에 해당하는 메시지를 조회한다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code 정보, 추가 argument로 현재 locale에 맞는 메시지를 조회한다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}

