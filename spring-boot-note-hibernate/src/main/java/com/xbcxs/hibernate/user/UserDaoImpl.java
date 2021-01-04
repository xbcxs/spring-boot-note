package com.xbcxs.hibernate.user;

import com.xbcxs.hibernate.user.domian.User;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 持久层实现
 *
 * @author Xiao
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public boolean addUser(User user) {
        entityManager.persist(user);
        return true;
    }

    @Override
    public boolean addBatchUser(List<User> list) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            entityManager.persist(list.get(i));
            if (i % 200 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        return true;
    }

    @Override
    public boolean deleteUser(String id) {
        entityManager.remove(entityManager.find(User.class, id));
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "update sys_user set name = :name where id = :id";
        NativeQuery nativeQuery = (NativeQuery) entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("name", user.getName());
        nativeQuery.setParameter("id", user.getId());
        int result = nativeQuery.executeUpdate();
        return result > 0 ? true : false;
    }

    @Override
    public boolean saveOrUpdateUser(User user) {
        User persistUser = entityManager.find(User.class, user.getId());
        persistUser.setName(persistUser.getName());
        entityManager.merge(persistUser);
        return true;
    }

    @Override
    public User getUser(String id) {
        String sql = "select * from sys_user where id = :id";
        NativeQuery nativeQuery = (NativeQuery) entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("id", id).addEntity(User.class);
        return (User) nativeQuery.uniqueResult();
    }

    @Override
    public List<User> listUser() {
        String sql = "select * from sys_user";
        NativeQuery nativeQuery = (NativeQuery) entityManager.createNativeQuery(sql);
        // 分页
        // nativeQuery.setFirstResult(from);
        // nativeQuery.setMaxResults(size);
        nativeQuery.addEntity(User.class);
        return nativeQuery.list();
    }

    // TODO 待验证，无效？
    @Override
    public List<UserDeptDO> listUserDept() {
        String sql = "select * from sys_user u ,sys_department d, sys_user_department ud where u.id=ud.user_id and ud.department_id=d.id";
        NativeQuery nativeQuery = (NativeQuery) entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(UserDeptDO.class));
        return nativeQuery.list();
    }

    @Override
    public List<UserDeptDO> listUserDeptForPage(int from, int size) {
        NativeQuery nativeQuery = scalarForPage(from, size);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(UserDeptDO.class));
        return nativeQuery.list();
    }

    @Override
    public List<Map> listMapUserDeptForPage(int from, int size) {
        NativeQuery nativeQuery = scalarForPage(from, size);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return nativeQuery.list();
    }

    /**
     * 该版本Hibernate在构建跨表联合返回对象时需要使用addScalar()构建属性映射。
     *
     * @param from
     * @param size
     * @return
     */
    private NativeQuery scalarForPage(int from, int size) {
        String sql = "select u.id as \"id\", u.login_name as \"loginName\", u.name as \"name\", u.phone as \"phone\", u.create_time as \"createTime\", u.update_time as \"updateTime\", d.name as \"departmentName\" from sys_user u ,sys_dept d, sys_user_dept ud where u.id=ud.user_id and ud.dept_id=d.id";
        NativeQuery nativeQuery = (NativeQuery) entityManager.createNativeQuery(sql);
        // 默认1表示第一页
        nativeQuery.setFirstResult(from);
        // 每页的条数
        nativeQuery.setMaxResults(size);
        nativeQuery.addScalar("id");
        nativeQuery.addScalar("loginName");
        nativeQuery.addScalar("name");
        nativeQuery.addScalar("phone");
        nativeQuery.addScalar("createTime");
        nativeQuery.addScalar("updateTime");
        nativeQuery.addScalar("departmentName");
        return nativeQuery;
    }

}
