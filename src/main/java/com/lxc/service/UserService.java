package com.lxc.service;

import com.lxc.entity.Manager;
import com.lxc.entity.User;

/**
 * @Description: TODO
 * @Author: SuMin
 * @Date: 2023/8/24
 **/
public interface UserService {

    public User selectByUserName(String username);
    public Integer updateUserById(User user);
}
