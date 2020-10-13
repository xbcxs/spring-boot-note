package com.xbcxs.mybatis.user;

import java.util.List;

/**
 * 用户业务层接口
 *
 * @author Xiao
 */
public interface UserService {

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User getUser(String id);

    /**
     * 分压查询用户
     * @param pageNum 当前页
     * @param pageSize 每页大小
     * @return
     */
    List<User> listUserForPage(int pageNum, int pageSize);

    /**
     * 查询用户集合
     *
     * @return
     */
    List<User> listUser();

    /**
     * 批量新增用户
     *
     * @param user
     * @return
     */
    boolean saveUser(User user);

    /**
     * 批量新增用户
     *
     * @Param list
     * @return
     */
    boolean saveBatchUser(List<User> list);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(int id);

}
