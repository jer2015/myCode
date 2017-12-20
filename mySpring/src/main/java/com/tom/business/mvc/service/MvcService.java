package com.tom.business.mvc.service;

import com.tom.business.mvc.model.User;

/**
 * @author lxl
 */
public interface MvcService {

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return true:成功 false:失败
     */
    Boolean addUser(User user);

    /**
     * 查询用户
     *
     * @param id 用户id
     * @return 用户实体
     */
    User getUser(String id);

    /**
     * 更新用户信息
     *
     * @param user 用户实体
     * @return true:成功 false:失败
     */
    Boolean updateUser(User user);
}
