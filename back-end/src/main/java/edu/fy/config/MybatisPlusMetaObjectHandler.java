package edu.fy.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import edu.fy.entity.enums.DeletedEnum;
import edu.fy.utils.UserUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis plus 自动填充配置
 * @author YangAo
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    /**
     * 配置插入时, (创建时间, 逻辑删除位)的填充
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        // 默认未删除
        this.strictInsertFill(metaObject, "isDeleted", DeletedEnum.class, DeletedEnum.NOT_DELETED);
        // 默认当前用户创建操作
        this.strictInsertFill(metaObject, "createId", Integer.class, UserUtil.getCurrentLoginUser().getStaff().getId());
    }

    /**
     * 配置更新时, 更新时间的填充
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        // 默认当前用户进行更新操作
        this.strictUpdateFill(metaObject, "updateId", Integer.class, UserUtil.getCurrentLoginUser().getStaff().getId());
    }
}
