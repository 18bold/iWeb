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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    Result4Json list(Integer page, Integer limit) {
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<User> users = userService.selectAll(rowBounds);
        Result4Json res = new Result4Json(0, "success", userService.selectCount(), users);
        System.out.println(res.toString());
        return res;
    }

    @RequestMapping(value = "/list/where", method = RequestMethod.GET)
    public @ResponseBody
    Result4Json list(Integer page, Integer limit, Integer min, Integer max, Integer roleId) {
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("min", RequestUtil.getInt(min, 0));
        map.put("max", RequestUtil.getInt(max, 0));
        map.put("roleId", RequestUtil.getInt(roleId, -1));
        List<User> users = userService.selectByWhere(rowBounds, map);
        Result4Json res = new Result4Json(0, "success", userService.selectByWhereCount(map), users);
        System.out.println(res.toString());
        return res;
    }

    @RequestMapping(value = "/{userId}/detail", method = RequestMethod.GET)
    public @ResponseBody
    User detail(@PathVariable("userId") Integer userId) {
        User user = userService.selectById(userId);
        return user;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    Result4Json upload(
            HttpServletRequest request,
            User user,
            @RequestParam("file") MultipartFile file) {
        System.out.println(user.toString());
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            System.out.println("file is not empty!");
            //上传文件路径
            String path = request.getServletContext().getRealPath("/images/");
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            try {
                file.transferTo(new File(path + File.separator + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Result4Json(200, "success", 0, null);
        } else {
            return new Result4Json(400, "fail", 0, null);
        }
    }

    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST,
            produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public String getUser(User user) {
        System.out.println("打印" + user.toString());
        return user.toString();
    }
}
