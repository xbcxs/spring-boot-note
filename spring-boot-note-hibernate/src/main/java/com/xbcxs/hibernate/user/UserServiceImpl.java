package com.xbcxs.hibernate.user;

import com.xbcxs.hibernate.user.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 业务层实现
 *
 * @author Xiao
 */
@Transactional(rollbackFor=Exception.class)
@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean deleteUser(String id) {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean saveOrUpdateUser(User user) {
        return userDao.saveOrUpdateUser(user);
    }

    @Override
    public User getUser(String id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Override
    public List<UserDeptDO> listUserDept() {
        return userDao.listUserDept();
    }

    @Override
    public List<UserDeptDO> listUserDeptForPage(int from, int size) {
        return userDao.listUserDeptForPage(from, size);
    }

    @Override
    public List<Map> listMapUserDeptForPage(int from, int size) {
        return userDao.listMapUserDeptForPage(from, size);
    }
}
