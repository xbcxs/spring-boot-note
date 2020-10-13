package com.xbcxs.mybatis.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UserController
 *
 * @author Xiao
 */
@RequestMapping("user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("query")
    public Object query(@RequestParam String id){
        logger.info("getUser coming...");
        return userService.getUser(id);
    }

    @RequestMapping("save_batch")
    public Object saveBatch(){
        List<User> list = new ArrayList<User>(2);
        User user1 = new User();
        user1.setName("name" + System.currentTimeMillis());
        user1.setLoginName("loginName" + System.currentTimeMillis());
        user1.setPhone("11111111111");
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());
        User user2 = new User();
        user2.setName("name" + System.currentTimeMillis());
        user2.setLoginName("loginName" + System.currentTimeMillis());
        user2.setPhone("12222222222");
        user2.setCreateTime(new Date());
        user2.setUpdateTime(new Date());
        list.add(user1);
        list.add(user2);
        return userService.saveBatchUser(list);
    }

    @RequestMapping("save")
    public Object save(){
        User user1 = new User();
        user1.setName("小明");
        user1.setLoginName("loginName_xm");
        user1.setPhone("123131231");
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());
        return userService.saveUser(user1);
    }

    @RequestMapping("list")
    public Object list(){
        return userService.listUser();
    }

    @RequestMapping("list_page")
    public Object listPage(@RequestParam int pageNum, @RequestParam int pageSize){
        return userService.listUserForPage(pageNum, pageSize);
    }

    @RequestMapping("update")
    public Object update(@RequestParam int id, @RequestParam String name){
        User user1 = new User();
        user1.setId(id);
        user1.setName(name);
        return userService.updateUser(user1);
    }

    @RequestMapping("delete")
    public Object delete(@RequestParam int id){
        return userService.deleteUser(id);
    }
}
