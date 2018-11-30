package com.spdb.controller;

        import com.spdb.annotation.ArchivesLog;
        import com.spdb.enums.LogEnum;
        import com.spdb.mapper.UserMapper;
        import com.spdb.model.User;
        import com.spdb.service.UserService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public int addUser(User user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    @ArchivesLog(LogEnum = LogEnum.DELETE,operationName = "登录",operationType = "1")
    public List<User> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return userService.findAllUser(pageNum,pageSize);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userMapper.selectByPrimaryKey(id);
    }
}
