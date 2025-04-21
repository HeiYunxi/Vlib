package com.fzsx.vlib.mapper;

import com.fzsx.vlib.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * vlib - UserMapper
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Component
public interface UserMapper {
    List<User> UserInfo(User user);
    void insertUser(@Param("userName")String name, @Param("account")String account,
                    @Param("email")String email,@Param("password")String password,
                    @Param("intro")String intro, @Param("phone")String phone, @Param("create_time")Timestamp create_time,
                    @Param("update_time")Timestamp update_time, @Param("type")int type);

    int saveUserInfo(User user);

    User getUserByAccountName(String accountName);

    User getUserByLoginInfo(Map<String, Object> map);

    /**
     * 分页查询
     * @param user
     * @return
     */
    List<User> queryUserList(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    int delete(User user);

    /**
     * 用户明细信息
     * @param user
     * @return
     */
    List<User> detail(User user);

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    int update(User user);
}
