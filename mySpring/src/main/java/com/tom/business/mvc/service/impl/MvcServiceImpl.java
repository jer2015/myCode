package com.tom.business.mvc.service.impl;

import com.tom.business.mvc.dao.UserMapper;
import com.tom.business.mvc.model.User;
import com.tom.business.mvc.service.MvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 */
@Service
public class MvcServiceImpl implements MvcService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean addUser(User user) {
        return userMapper.insertSelective(user) > 0;
    }

    @Override
    public User getUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        // System.out.println(1/0);
        return i > 0;
    }
}
