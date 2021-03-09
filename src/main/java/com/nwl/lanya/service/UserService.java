package com.nwl.lanya.service;

import com.nwl.lanya.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * @author misaki
 */

public interface UserService {

    /**
     * 查询邮箱是否已注册
     *
     * @param dto user传输对象
     * @return 是否已注册
     */
    boolean findByEmail(UserDto dto);

    /**
     * 用户注册
     * @param dto user传输对象
     */
    void register(UserDto dto);


    /**
     * 激活验证码
     * @param id 用户id
     */
    void activation(String id);

    /**
     * 账户登录状态值：1，2，3；1：邮箱密码不匹配；2：账户未激活；3：登录成功
     * @param dto usr dto
     * @return  账户登录状态值
     */
    int logOn(UserDto dto);
}
