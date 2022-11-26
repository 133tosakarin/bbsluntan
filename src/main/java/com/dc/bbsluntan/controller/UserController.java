package com.dc.bbsluntan.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.bbsluntan.common.R;
import com.dc.bbsluntan.common.StringUtils;
import com.dc.bbsluntan.entity.TopicEntity;
import com.dc.bbsluntan.entity.UserEntity;
import com.dc.bbsluntan.service.TopicService;
import com.dc.bbsluntan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.LambdaMetafactory;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author DengChao
 * baseUrl =/user/jsof
 */
@Slf4j
@RestController
@RequestMapping("/user") //请求路径
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @PostMapping("/login") //user/login
    public R<UserEntity> login(@RequestBody UserEntity user,HttpServletRequest req){
        log.info("登录中");
        if(user==null || user.getUsername()==null )
            return R.error("error");
        //对密码进行加密
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        LambdaQueryWrapper<UserEntity> querryWrapper = new LambdaQueryWrapper<>();
        querryWrapper.eq(UserEntity::getUsername,user.getUsername());
        UserEntity one = userService.getOne(querryWrapper);
        if(one!=null){
            if(StringUtils.equal(one.getPassword(),password))
            {
                log.info("登录成功");
                one.setUserStatus(1);
                req.getSession().setAttribute("user",one.getId());
                return R.success(one);

            }else{
                return R.error("error");
            }
        }
        return R.error("error");
    }
    @PostMapping("/register")
    public R<String> register(@RequestBody UserEntity user){
        log.info("注册");
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //从数据库中查找有无相同的账户id
        LambdaQueryWrapper<UserEntity> queryWrapper  =  new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername,user.getUsername());
        UserEntity newUser = userService.getOne(queryWrapper);
        if(newUser==null){
            log.info("注册成功");
            user.setPassword(password);
            userService.save(user);
            return R.success("success");
        }
        return R.error("error");
    }
//baseUrl =  http://localhost:8080/
// base+ user/regist
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest req){
        Long id  = (Long) req.getSession().getAttribute("user");
        UserEntity user = userService.getById(id);
        user.setUserStatus(0);
        req.getSession().removeAttribute("user");

        return R.success("success");
    }

    @PutMapping("/update")
    public R<String> update(@RequestBody UserEntity updateUser,HttpServletRequest req){
        log.info("修改用户信息");
        if(updateUser.getPassword()==null){
            return R.error("error");
        }
        //String userPassword = updateUser.getUserPassword();
        //userPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
        //updateUser.setUserPassword(userPassword);
        userService.updateById(updateUser);

        return R.success("success");
    }

    @PostMapping("/mytopic")
    public R<Page<TopicEntity>> showMyTopic(int page,int pageSize,HttpServletRequest req){
        log.info("获取用户发布的帖子");
        Long userId = (Long) req.getSession().getAttribute("user");
        Page<TopicEntity> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<TopicEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TopicEntity::getUserId,userId);
        Page<TopicEntity> page1 = topicService.page(pageInfo, wrapper);
        return R.success(page1);
    }
    @GetMapping("/get")
    public R<UserEntity> getUser(HttpServletRequest req){
        Long id = (Long) req.getSession().getAttribute("user");
        if(id == null)
            id = 2l;
        UserEntity user = userService.getById(id);
        return R.success(user);
    }

}
