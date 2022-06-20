package edu.fy.utils;

import edu.fy.entity.dto.LoginUserDTO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author YangAo
 * @Description 登录用户工具类
 * @create 2022-06-13 20:10
 */
public class UserUtil {

    /**
     * 获取当前登录的用户信息
     * @return 当前登录的用户信息
     */
    public static LoginUserDTO  getCurrentLoginUser() {
        return (LoginUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
