package com.dc.bbsluntan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.bbsluntan.common.R;
import com.dc.bbsluntan.entity.ReplyEntity;
import com.dc.bbsluntan.entity.TopicEntity;
import com.dc.bbsluntan.entity.UserEntity;
import com.dc.bbsluntan.mapper.TopicMapper;
import com.dc.bbsluntan.mapper.UserMapper;
import com.dc.bbsluntan.service.ReplyService;
import com.dc.bbsluntan.service.TopicService;
import com.dc.bbsluntan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DengChao
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, TopicEntity> implements TopicService {

    @Autowired
    private ReplyService replyService;

    private List<ReplyEntity> getReplys(Long topicId){
        LambdaQueryWrapper<ReplyEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReplyEntity::getTopicId,topicId);
        wrapper.isNull(ReplyEntity::getReplyFloor);
        List<ReplyEntity> replys = replyService.list(wrapper);
        replys = replys.stream().map(item->{
            item = replyService.getReplyAndFloor(item.getReplyId());
            return item;
        }).collect(Collectors.toList());
        return replys;
    }

    @Override
    public TopicEntity selectTopicAndReply(Long topicId) {
        TopicEntity topic = getById(topicId);

        topic.setReplys(getReplys(topicId));
        return topic;
    }

    @Override
    public List<TopicEntity> listAndReply(Long sectionId) {
        LambdaQueryWrapper<TopicEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TopicEntity::getSectionId,sectionId);
        List<TopicEntity> topics = list(wrapper);
        topics.forEach(item->{
            item.setReplys(getReplys(item.getTopicId()));
        });
        return topics;
    }
}
