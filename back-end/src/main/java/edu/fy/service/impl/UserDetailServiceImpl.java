package edu.fy.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import edu.fy.entity.Staff;
import edu.fy.entity.dto.LoginUserDTO;
import edu.fy.mapper.StaffMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author poppy
 * @Description 验证过程中 从数据库中查取用户信息
 * @create 2022-06-13 19:11
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        // 查询对应的用户信息
        Staff staff;
        try {
            staff = new LambdaQueryChainWrapper<>(staffMapper).eq(Staff::getPhone, phone).one();
        } catch (Exception e) {
            throw new RuntimeException("数据库出现错误");
        }
        if (Objects.isNull(staff)) {
            throw new UsernameNotFoundException("手机号错误");
        }
        // todo: 查询对应的权限信息

        // 将信息封装后进行返回
        return new LoginUserDTO(staff, null);
    }

    private final StaffMapper staffMapper;

    public UserDetailServiceImpl(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }
}
