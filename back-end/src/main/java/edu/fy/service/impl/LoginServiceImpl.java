package edu.fy.service.impl;

import edu.fy.entity.dto.LoginInfoDTO;
import edu.fy.entity.dto.LoginUserDTO;
import edu.fy.service.LoginService;
import edu.fy.utils.CommonConstString;
import edu.fy.utils.JwtUtil;
import edu.fy.utils.RedisCache;
import edu.fy.utils.UserUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author poppy
 * @Description
 * @create 2022-06-13 19:37
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Map<String, Object> login(LoginInfoDTO loginInfoDTO) {
        // 调用 spring security 中的验证操作
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInfoDTO.getPhone(), loginInfoDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        // 生成 token
        LoginUserDTO loginUserDTO = (LoginUserDTO) authenticate.getPrincipal();
        Integer staffId = loginUserDTO.getStaff().getId();
        String token = JwtUtil.createJWT(staffId.toString());
        // 存入 redis
        redisCache.setCacheObject(CommonConstString.LOGIN_USER_HEADER + staffId, loginUserDTO);
        // 返回token
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("token", token);
        map.put("userId", staffId);
        return map;
    }

    @Override
    public Boolean logout() {
        Integer id = UserUtil.getCurrentLoginUser().getStaff().getId();
        // 删除redis中存储的用户信息
        return redisCache.deleteObject(CommonConstString.LOGIN_USER_HEADER + id);
    }

    private final AuthenticationManager authenticationManager;
    private final RedisCache redisCache;

    public LoginServiceImpl(AuthenticationManager authenticationManager, RedisCache redisCache) {
        this.authenticationManager = authenticationManager;
        this.redisCache = redisCache;
    }
}
