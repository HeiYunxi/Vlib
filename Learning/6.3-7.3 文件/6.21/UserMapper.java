package com.fzsx.mapper;

import com.fzsx.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {
    int saveUserInfo(User user);

    /**
     *
     * @param accountName
     * @return
     */
    User getUserByAccountName(String accountName);

    /**
     *
     * @param map
     * @return
     */
    User getUserByLoginInfo(Map<String,Object> map);

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
}
