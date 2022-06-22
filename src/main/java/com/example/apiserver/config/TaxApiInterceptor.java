package com.example.apiserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TaxApiInterceptor implements HandlerInterceptor {
//    private final ObjectMapper objectMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("## TaxApiInterceptor: preHandle(): {}", request.getRequestURI());
        log.info("## TaxApiInterceptor: preHandle(): User-Agent : {}", request.getHeader("User-Agent"));
        log.info("## TaxApiInterceptor: preHandle(): CI : {}", request.getHeader("ci"));
        log.info("## TaxApiInterceptor: preHandle(): DevUid : {}", request.getHeader("uid"));
        log.info("## TaxApiInterceptor: preHandle(): jwtToken : {}", request.getHeader("jwt"));

        MDC.put("ci", request.getHeader("ci"));
        MDC.put("uid", request.getHeader("uid"));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
/*
        log.info("## postHandle: [1] {}", MDC.get("ci"));
        Thread.sleep(5000);
        log.info("## postHandle: [2] {}", MDC.get("ci"));
*/
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
//        String tmp = getResponseBody(response);
//        log.info("## {}", tmp);
    }

    private String getResponseBody(final HttpServletResponse response) throws IOException {
        String payload = null;
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            wrapper.setCharacterEncoding("UTF-8");
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                wrapper.copyBodyToResponse();
            }
        }
        return null == payload ? " - " : payload;
    }

}
