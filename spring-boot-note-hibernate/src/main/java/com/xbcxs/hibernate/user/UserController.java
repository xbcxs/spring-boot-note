package com.xbcxs.hibernate.user;

import com.xbcxs.hibernate.user.domian.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层
 *
 * @author Xiao
 */
@RequestMapping("user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public Object register(@RequestParam String name){
        User userDo = new User();
        userDo.setName("TestName-" + name);
        userDo.setCreateTime(new java.util.Date());
        userDo.setLoginName("LoginName" + System.currentTimeMillis());
        userDo.setPhone("1234567890");
        userDo.setUpdateTime(new java.util.Date());
        return userService.addUser(userDo);
    }

    @RequestMapping("delete")
    public Object delete(@RequestParam String id ){
        return userService.deleteUser(id);
    }

    @RequestMapping("edit")
    public Object edit(@RequestParam String id, @RequestParam String name){
        User userDo = new User();
        userDo.setName(name);
        userDo.setId(id);
        return userService.updateUser(userDo);
    }

    @RequestMapping("query")
    public Object query(@RequestParam String id){
        return userService.getUser(id);
    }

    @RequestMapping("list")
    public Object list(){
        return userService.listUser();
    }

    @RequestMapping("list_page")
    public Object listPage(@RequestParam int from, @RequestParam int size){
        return userService.listUserDeptForPage(from, size);
    }

    @RequestMapping("list_map_page")
    public Object ListMapPage(@RequestParam int from, @RequestParam int size){
        return userService.listMapUserDeptForPage(from, size);
    }
}
