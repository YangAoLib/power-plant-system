package edu.fy.entity.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import edu.fy.entity.Staff;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author poppy
 * @Description 登录的用户信息详情
 * @create 2022-06-13 19:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "登录的用户信息详情")
public class LoginUserDTO implements UserDetails {

    public LoginUserDTO(Staff staff, List<String> permission) {
        this.staff = staff;
        this.permission = permission;
    }

    /**
     * 用户基础信息
     */
    @Schema(description = "用户基础信息")
    private Staff staff;

    /**
     * 权限信息, 字符串形式
     */
    @Schema(description = "权限信息, 字符串形式")
    private List<String> permission;

    /**
     * 权限信息, spring security 内置对象形式
     * 序列化忽略该属性
     */
    @JSONField(serialize = false)
    @Schema(description = "权限信息, spring security 内置对象形式")
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 如果有缓存的对象形式的权限信息, 则直接返回
        if (Objects.nonNull(authorities)) {
            return authorities;
        }
        // 将字符串形式转换后存储, 并返回
        authorities = CollectionUtils.isEmpty(permission) ? new ArrayList<>() : permission.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return staff.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO: 本次验证使用的是手机号进行验证
        return staff.getPhone();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private static final long serialVersionUID = 1937414692381228848L;
}
