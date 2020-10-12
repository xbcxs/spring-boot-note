package com.xbcxs.hibernate.user;

import com.xbcxs.hibernate.user.domian.User;

import java.util.List;
import java.util.Map;

/**
 * 业务层接口
 *
 * @author Xiao
 */
public interface UserService {

    /**
     * 新增
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteUser(String id);

    /**
     * 修改
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 新增或修改
     * @param user
     * @return
     */
    boolean saveOrUpdateUser(User user);

    /**
     * 通过addEntity(UserDO.class)返回DO
     *
     * @param id
     * @return
     */
    User getUser(String id);

    /**
     * 通过addEntity(User.class)返回List<User>
     *
     * @return
     */
    List<User> listUser();

    /**
     * 通过addScalar(attribute)返回List<UserDept>
     *
     * @return
     */
    List<UserDeptDO> listUserDept();

    /**
     * 通过addScalar(attribute)返回分页List<UserDept>
     * @param from
     * @param size
     * @return
     */
    List<UserDeptDO> listUserDeptForPage(int from, int size);

    /**
     * 通过addScalar(attribute)返回分页List<<Map>
     *
     * @param from
     * @param size
     * @return
     */
    List<Map> listMapUserDeptForPage(int from, int size);
}
