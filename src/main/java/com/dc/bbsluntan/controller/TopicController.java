package com.dc.bbsluntan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.bbsluntan.common.R;
import com.dc.bbsluntan.entity.TopicEntity;
import com.dc.bbsluntan.entity.UserEntity;
import com.dc.bbsluntan.service.TopicService;
import com.dc.bbsluntan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DengChao
 */
@Slf4j
@RequestMapping("/topic")
@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;



    /**
     * 分页展示所有首页推荐帖子
     * @param page
     * @param pageSize
     * @return
     *
     * url/listAll?page = 2 & pageSize = 100
     */
    @GetMapping("/listAll")
    public R<Page<TopicEntity>> listAll(Integer page,Integer pageSize,String condition){
        log.info("获取所有话题列表");
        Page<TopicEntity> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<TopicEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(condition == null || condition == "null")
            System.out.println("yes");
        condition = (condition == null || "null".equals(condition)) ? "":condition;
        lambdaQueryWrapper.like(TopicEntity::getTopicTitle,condition);
        lambdaQueryWrapper.orderByDesc(TopicEntity::getUpdateTime);
        Page<TopicEntity> page1 = topicService.page(pageInfo, lambdaQueryWrapper);
        List<TopicEntity> records = pageInfo.getRecords();
        records = records.stream().map(item -> {
            item = topicService.selectTopicAndReply(item.getTopicId());
            System.out.println(item.getReplys().size());
            return item;
        }).collect(Collectors.toList());
        pageInfo.setRecords(records);
        return R.success(page1);
    }
    /**
     * 首页帖子
     * @param topic
     * @param req
     * @return
     */
    @PostMapping("/create")
    public R<TopicEntity> createTopic(@RequestBody TopicEntity topic, HttpServletRequest req){
        log.info("发布帖子");
        Long id = (Long)req.getSession().getAttribute("user");
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper() ;
/*        if(id==null)
            id = (Long) 1586600449878847489l;*/
        wrapper.eq(UserEntity::getId,id);
        UserEntity user = userService.getOne(wrapper);

        topic.setUserId(user.getId());
        topic.setTopicClickCount(0);
        topic.setTopicReplyCount(0);
        boolean flag = topicService.save(topic);
        return flag?R.success(topic):R.error("error");
    }

    /**
     * 删除帖子
     * @param ids
     * @return
     */

    @DeleteMapping("/del")
    public R<String> delTopic(@RequestParam List<Long> ids){
        if(ids!=null || ids.size()>0)
            topicService.removeByIds(ids);
        return R.success("success");
    }

    @GetMapping("/detail")
    public R<TopicEntity> showDetail(Long id){
        log.info("展示话题详情");
        TopicEntity topicEntity = topicService.selectTopicAndReply(id);
        Integer topicClickCount = topicEntity.getTopicClickCount();
        System.out.println("topicClick = "+topicClickCount);
        topicEntity.setTopicClickCount(topicClickCount+1);
        topicService.updateById(topicEntity);
        return  R.success(topicEntity);
    }





}
