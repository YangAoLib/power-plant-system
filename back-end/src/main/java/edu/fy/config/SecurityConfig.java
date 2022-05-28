package edu.fy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        // 暂时对所有的请求都不拦截
        http.authorizeHttpRequests().anyRequest().permitAll().and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = passwordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("yangaoAdmin").password(encoder.encode("nswdfq")).roles("admin");
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
}
