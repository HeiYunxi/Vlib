package com.fzsx.vlib.mapper;

import com.fzsx.vlib.entity.Role;
import com.fzsx.vlib.entity.User;
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
     *
     * @param map
     * @return
     */
    User getUserIdByAccountName(String accountName);

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
    List<User>  detail(User user);

    /**
     * 用户信息更新
     * @param user
     * @return
     */
     int  update(User user);


}
