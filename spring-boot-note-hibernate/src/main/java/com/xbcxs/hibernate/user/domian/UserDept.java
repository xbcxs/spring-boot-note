package com.xbcxs.hibernate.user.domian;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户部门关联对象
 *
 * @author Xiao
 */
@Entity
@Table(name = "SYS_USER_DEPT")
public class UserDept {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "DEPT_ID")
    private String deptId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
