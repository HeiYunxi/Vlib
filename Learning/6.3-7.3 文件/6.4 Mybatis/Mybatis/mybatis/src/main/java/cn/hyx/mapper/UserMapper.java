package cn.hyx.mapper;

import cn.hyx.entity.User;

import java.util.List;

/**
 * mybatis - Usermapper
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public interface UserMapper {
    List<User> selectAll();
}
