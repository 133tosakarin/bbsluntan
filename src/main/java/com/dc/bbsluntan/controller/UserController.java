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
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @PostMapping("/login")
    public R<UserEntity> login(@RequestBody UserEntity user,HttpServletRequest req){
        log.info("登录中");
        if(user==null || user.getUserName()==null )
            return R.error("error");
        //对密码进行加密
        String password = user.getUserPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        LambdaQueryWrapper<UserEntity> querryWrapper = new LambdaQueryWrapper<>();
        querryWrapper.eq(UserEntity::getUserAccountId,user.getUserAccountId());
        UserEntity one = userService.getOne(querryWrapper);
        if(one!=null){
            if(StringUtils.equal(one.getUserPassword(),password))
            {
                log.info("登录成功");
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

        String password = user.getUserPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //从数据库中查找有无相同的账户id
        LambdaQueryWrapper<UserEntity> queryWrapper  =  new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUserAccountId,user.getUserAccountId());
        UserEntity newUser = userService.getOne(queryWrapper);
        if(newUser==null){
            user.setUserPassword(password);
            userService.save(user);
            return R.success("success");
        }
        return R.error("error");
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest req){
        req.getSession().removeAttribute("user");
        return R.success("success");
    }

    @PutMapping("/update")
    public R<String> update(@RequestBody UserEntity updateUser){
        log.info("修改用户信息");
        if(updateUser.getUserPassword()==null){
            return R.error("error");
        }
        String userPassword = updateUser.getUserPassword();
        userPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
        updateUser.setUserPassword(userPassword);
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


}
