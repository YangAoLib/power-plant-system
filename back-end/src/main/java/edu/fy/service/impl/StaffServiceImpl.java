package edu.fy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fy.entity.Staff;
import edu.fy.mapper.StaffMapper;
import edu.fy.service.StaffService;
import edu.fy.utils.CustomException;
import edu.fy.utils.ResultCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        staff.setPassword(PASSWORD_ENCODER.encode(staff.getPassword()));
        // 身份证号不能重复, 先查询有无对应的身份证号, 再进行操作
        if (list(new LambdaQueryWrapper<Staff>().eq(Staff::getCardId, staff.getCardId())).size() > 0) {
            throw new CustomException("身份证号重复", ResultCode.USER_IS_EXISTED);
        }
        return save(staff);
    }

    private final PasswordEncoder PASSWORD_ENCODER;

    public StaffServiceImpl(PasswordEncoder passwordEncoder) {
        this.PASSWORD_ENCODER = passwordEncoder;
    }
}




