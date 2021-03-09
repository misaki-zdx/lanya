package com.nwl.lanya.service;

import com.nwl.lanya.common.SendMailUtil;
import com.nwl.lanya.dao.UserMapper;
import com.nwl.lanya.dto.UserDto;
import com.nwl.lanya.po.User;
import com.nwl.lanya.po.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author misaki
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public boolean findByEmail(UserDto dto) {
        boolean exist = false;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(dto.getPo().getEmail());
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
            po.setId(UUID.randomUUID().toString().replace("-", ""));
            SendMailUtil.customSend(po.getEmail(), "邮箱注册", "http://127.0.0.1:8888/activation/" + po.getId());
            po.setIsUsable(0);
            mapper.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activation(String id) {
        User user = mapper.selectByPrimaryKey(id);
        user.setIsUsable(1);
        mapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 账户登录状态值：1，2，3；1：邮箱密码不匹配；2：账户未激活；3：登录成功
     * @param dto usr dto
     * @return  账户登录状态值
     */
    @Override
    public int logOn(UserDto dto) {
        int flag = 1;
        List<User> users = mapper.logOn(dto.getPo());
        String pwd = dto.getPo().getPwd();
        if (null != users && users.size() >= 1 && null != pwd) {
            if (users.get(0).getIsUsable() != 0) {
                if (users.get(0).getPwd().equals(pwd) && users.get(0).getIsUsable() == 1) {
                    flag = 3;
                }
            }else {
                flag = 2;
            }
        }
        return flag;
    }
}
