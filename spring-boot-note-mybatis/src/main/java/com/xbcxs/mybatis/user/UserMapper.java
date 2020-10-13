package com.xbcxs.mybatis.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserMapper
 *
 * @author Xiao
 */
@Mapper
public interface UserMapper {

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    User getUser(String id);

    /**
     * 获取用户集合
     *
     * @return
     */
    List<User> listUser();

    /**
     * 获取用户分页
     *
     * @return
     */
    List<User> listUserForPage();

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    boolean save(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean delete(int id);
}
