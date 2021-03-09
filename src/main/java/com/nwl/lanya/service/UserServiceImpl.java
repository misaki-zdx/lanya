package com.nwl.lanya.service;

import com.nwl.lanya.common.SendMailUtil;
import com.nwl.lanya.dao.UserMapper;
import com.nwl.lanya.dto.UserDto;
import com.nwl.lanya.po.User;
import com.nwl.lanya.po.UserExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author misaki
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public boolean findByEmail(UserDto dto) {
        boolean exist = false;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(dto.getPo().getId());
        List<User> users = mapper.selectByExample(userExample);
        if (users.size() > 0) {
            exist = true;
        }
        return exist;
    }

    @Override
    public void register(UserDto dto) {
        User po = dto.getPo();
        try {
            SendMailUtil.customSend(po.getEmail(), "邮箱注册", "http:8888/login/"+po.getId());
            po.setId(UUID.randomUUID().toString().replace("-",""));
            po.setIsUsable(0);
            mapper.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
