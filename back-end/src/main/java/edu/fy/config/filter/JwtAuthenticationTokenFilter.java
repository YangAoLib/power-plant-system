package edu.fy.config.filter;

import edu.fy.entity.dto.LoginUserDTO;
import edu.fy.utils.CommonConstString;
import edu.fy.utils.JwtUtil;
import edu.fy.utils.RedisCache;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YangAo
 * @Description 通过 token 进行用户是否登录的验证 过滤器
 * @create 2022-06-13 20:40
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取token
        String token = request.getHeader(CommonConstString.TOKEN_HEADER);
        if (!StringUtils.hasText(token)) {
            // 没有获取到token, 直接放行并且不执行后面的代码
            filterChain.doFilter(request, response);
            return ;
        }
        // 解析出 userId
        String userId;
        try {
            userId = JwtUtil.parseJWT(token).getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token 格式错误");
        }
        // 从redis中获取登录用户的信息
        LoginUserDTO loginUserDTO = redisCache.getCacheObject(CommonConstString.LOGIN_USER_HEADER + userId);
        // 将用户信息存入 SpringSecurityContext
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDTO, null, loginUserDTO.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }

    private final RedisCache redisCache;

    public JwtAuthenticationTokenFilter(RedisCache redisCache) {
        this.redisCache = redisCache;
    }
}
