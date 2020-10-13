package com.xbcxs.mybatis.user;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户业务层实现
 *
 * @author Xiao
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    @Override
    public List<User> listUserForPage(int pageNum, int pageSize) {
        // 分页插件，必须在分页语句最近处之前执行
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.listUserForPage();
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveUser(User user) {
        return userMapper.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatchUser(List<User> list) {
        boolean result = true;
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            UserMapper um = sqlSession.getMapper(UserMapper.class);
            int count = list.size();
            for(int i = 0; i<count; i++){
                um.save(list.get(i));
                if(i % 200 == 0 ){
                    // 当你将 ExecutorType 设置为 ExecutorType.BATCH 时，可以使用这个方法清除（执行）缓存在 JDBC 驱动类中的批量更新语句。
                    sqlSession.flushStatements();
                }
            }
            sqlSession.commit();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userMapper.delete(id);
    }
}
