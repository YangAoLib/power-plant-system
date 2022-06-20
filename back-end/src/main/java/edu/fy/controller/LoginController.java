package edu.fy.controller;

import edu.fy.entity.dto.LoginInfoDTO;
import edu.fy.service.LoginService;
import edu.fy.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author YangAo
 * @Description 登录 Controller
 * @create 2022-06-13 19:07
 */
@RestController
@Tag(name = "登录 Controller", description = "人员登录的Controller")
public class LoginController {

    @PostMapping("/user/login")
    @Operation(summary = "用户登录")
    public Result<Map<String, Object>> login(@RequestBody @Validated LoginInfoDTO loginInfoDTO) {
        return Result.success(loginService.login(loginInfoDTO));
    }

    @GetMapping("/user/logout")
    @Operation(summary = "用户退出")
    public Result<Boolean> logout() {
        return Result.success(loginService.logout());
    }



    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
}
