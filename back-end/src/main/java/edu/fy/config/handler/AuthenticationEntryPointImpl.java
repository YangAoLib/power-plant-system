package edu.fy.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fy.utils.Result;
import edu.fy.utils.ResultCode;
import edu.fy.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YangAo
 * @Description 认证异常处理器
 * @create 2022-06-11 21:39
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 要返回的信息
        Result<String> result = Result.fail(ResultCode.UNAUTHORIZED, authException.getLocalizedMessage());
        String json = new ObjectMapper().writeValueAsString(result);
        // 将信息返回
        WebUtils.renderString(response, json);
    }
}
