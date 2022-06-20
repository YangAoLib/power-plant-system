package edu.fy.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fy.utils.Result;
import edu.fy.utils.ResultCode;
import edu.fy.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YangAo
 * @Description 授权异常处理类
 * @create 2022-06-11 21:43
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 要返回的信息
        Result<String> result = Result.fail(ResultCode.FORBIDDEN, accessDeniedException.getLocalizedMessage());
        String json = new ObjectMapper().writeValueAsString(result);
        // 返回信息
        WebUtils.renderString(response, json);
    }
}
