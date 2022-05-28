package edu.fy.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.Staff;
import edu.fy.mapper.StaffMapper;
import edu.fy.service.StaffService;
import edu.fy.utils.CustomException;
import edu.fy.utils.ResultCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author YangAo
* @description 针对表【staff(人员信息)】的数据库操作Service实现
* @createDate 2022-05-26 10:09:49
*/
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff>
    implements StaffService{

    @Override
    public Boolean saveStaff(Staff staff) {
        // 密码加密, 其他大部分验证通过 spring boot validation 进行完成
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        // 身份证号不能重复, 先查询有无对应的身份证号, 再进行操作
        if (new LambdaQueryChainWrapper<>(baseMapper).eq(Staff::getCardId, staff.getCardId()).list().size() > 0) {
            throw new CustomException("身份证号重复", ResultCode.USER_IS_EXISTED);
        }
        // 手机号不能重复
        if (new LambdaQueryChainWrapper<>(baseMapper).eq(Staff::getPhone, staff.getPhone()).list().size() > 0) {
            throw new CustomException("手机号重复", ResultCode.USER_IS_EXISTED);
        }
        return save(staff);
    }

    @Override
    public Boolean updateStaff(Staff staff) {
        // id 不能为空
        if (staff.getId() == null)
            throw new CustomException("id不能为空", ResultCode.PARAM_IS_INVALID);
        // 密码不为空, 则进行加密后再用于更新
        String password = staff.getPassword();
        if (StringUtils.hasText(password))
            staff.setPassword(passwordEncoder.encode(password));
        // 根据id进行人员信息更新
        return updateById(staff);
    }

    private final PasswordEncoder passwordEncoder;

    public StaffServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}




