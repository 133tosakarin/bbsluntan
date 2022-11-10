package com.dc.bbsluntan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.bbsluntan.common.R;
import com.dc.bbsluntan.entity.ReplyEntity;
import com.dc.bbsluntan.entity.TopicEntity;
import com.dc.bbsluntan.service.ReplyService;
import com.dc.bbsluntan.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author DengChao
 */
@Slf4j
@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private TopicService topicService;
    /**
     *接收话题id和要回复的内容
     * @param req
     * @return
     */
    @PostMapping("/answer")
    public R<ReplyEntity> reply(@RequestParam("topicId") Long topicId,@RequestParam("content")String content, HttpServletRequest req){
        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setTopicId(topicId);
        Long userId = (Long) req.getSession().getAttribute("user");
        replyEntity.setUserId(userId);
        replyEntity.setReplyContents(content);
        replyEntity.setReplyClickCount(0);
        boolean flag = replyService.save(replyEntity);
        return flag ? R.success(replyEntity) : R.error("error");
    }

    @GetMapping ("/list")
    public R<Page<ReplyEntity>> replyList(int page,int pageSize,Long topicId){
        Page<ReplyEntity> pageInfo = new Page<>();
        LambdaQueryWrapper<ReplyEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReplyEntity::getTopicId,topicId);
        replyService.page(pageInfo,wrapper);
        return R.success(pageInfo);
    }

    @DeleteMapping("/del")
    public R<String>deleteReply(Long replyId){
        boolean flag = replyService.removeById(replyId);
        return flag?R.success("删除成功"):R.error("删除失败");
    }
}
