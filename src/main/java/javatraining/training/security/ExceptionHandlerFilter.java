package javatraining.training.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javatraining.training.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Adela Vasilache on 02.05.2018
 */
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    public static final String EXPIRED = "JWT expired";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String JSON = "application/json";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (RuntimeException e) {
            ErrorDto errorDto = ErrorDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).
                    message(e.getMessage()).build();
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            if (e.getMessage().contains(EXPIRED)) {
                httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                errorDto = ErrorDto.builder().status(HttpStatus.BAD_REQUEST).
                        message(e.getMessage()).build();
            }
            httpServletResponse.addHeader(CONTENT_TYPE, JSON);
            httpServletResponse.getWriter().write(convertObjectToJson(errorDto));
        }
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
