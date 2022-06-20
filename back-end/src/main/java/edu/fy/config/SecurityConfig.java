package edu.fy.config;

import edu.fy.config.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * 开启安全
 *
 * @author YangAo
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf
                .csrf().disable()
                // 不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 进行访问路径配置
                .authorizeRequests()
                // 登录接口 匿名访问(没登录可以访问, 登录后不可以访问)
                .antMatchers("/user/login").anonymous()
                // 配置swagger访问
                .antMatchers("/swagger**/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/v3/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // 添加jwt认证过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 开启跨域
                .cors()
                .and()
                // 配置认证授权异常处理器
                .exceptionHandling()
                // 认证异常处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                // 授权异常处理器, 一般是权限不足时触发
                .accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * 编码器的生成
     *
     * @return 编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 默认查找的编码器
        String idForEncoder = "bcrypt";
        // 添加需要包含的编码器
        Map<String, PasswordEncoder> map = new HashMap<>(5);
        map.put(idForEncoder, new BCryptPasswordEncoder());

        return new DelegatingPasswordEncoder(idForEncoder, map);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter, AuthenticationEntryPoint authenticationEntryPoint, AccessDeniedHandler accessDeniedHandler) {
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }
}
