package com.nwl.lanya.service;

import com.nwl.lanya.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * @author misaki
 */
@Service
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



}
