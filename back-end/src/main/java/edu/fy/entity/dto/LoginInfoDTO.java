package edu.fy.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author poppy
 * @Description 登录信息传输类
 * @create 2022-06-13 19:08
 */
@Data
@Schema(description = "登录信息传输类")
public class LoginInfoDTO {

    /**
     * 手机号, 采用手机号登录
     */
    @Pattern(regexp = "^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "手机号格式有误")
    @Schema(description = "用于登录的手机号")
    private String phone;

    /**
     * 密码
     */
    @NotBlank
    @Schema(description = "密码")
    private String password;
}
