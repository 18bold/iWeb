package com.bold.web;

import com.bold.dto.Result4Json;
import com.bold.entity.User;
import com.bold.service.UserService;
import com.bold.utils.RequestUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

import static com.bold.utils.RequestUtil.getInt;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody Result4Json list(Integer page, Integer limit) {
        int offset = (page-1)*limit;
        RowBounds rowBounds = new RowBounds(offset,limit);
        List<User> users = userService.selectAll(rowBounds);
        Result4Json res = new Result4Json(0,"success",userService.selectCount(),users);
        System.out.println(res.toString());
        return res;
    }

    @RequestMapping(value = "/list/where", method = RequestMethod.GET)
    public @ResponseBody Result4Json list(Integer page, Integer limit, Integer min, Integer max, Integer roleId) {
        int offset = (page-1)*limit;
        RowBounds rowBounds = new RowBounds(offset,limit);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("min",RequestUtil.getInt(min,0));
        map.put("max",RequestUtil.getInt(max,0));
        map.put("roleId", RequestUtil.getInt(roleId,-1));
        List<User> users = userService.selectByWhere(rowBounds,map);
        Result4Json res = new Result4Json(0,"success",userService.selectByWhereCount(map),users);
        System.out.println(res.toString());
        return res;
    }

    @RequestMapping(value = "/{userId}/detail", method = RequestMethod.GET)
    public @ResponseBody User detail(@PathVariable("userId") Integer userId) {
        User user = userService.selectById(userId);
        return user;
    }
}
