package edu.fy.service;

import edu.fy.entity.dto.LoginInfoDTO;

import java.util.Map;

/**
 * @author poppy
 * @Description 登录服务
 * @create 2022-06-13 19:35
 */
public interface LoginService {

    /**
     * 登录操作
     * @param loginInfoDTO 登录信息
     * @return token 与 用户id
     */
    Map<String, Object> login(LoginInfoDTO loginInfoDTO);

    /**
     * 退出操作
     * @return 是否退出成功
     */
    Boolean logout();
}
