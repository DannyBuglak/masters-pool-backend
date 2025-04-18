package com.masters.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private static final String API_KEY_HEADER = "x-api-key";
    private static final String VALID_API_KEY = "your-api-key-here";  // TODO Pull in from environment variables - probably use terraform files for AWS Secrets Manager

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Extract the API key from the request header
        String apiKey = request.getHeader(API_KEY_HEADER);

        // Check if the API key is valid
        if (VALID_API_KEY.equals(apiKey)) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Forbidden: Invalid API Key");
            return false;
        }
    }

}
