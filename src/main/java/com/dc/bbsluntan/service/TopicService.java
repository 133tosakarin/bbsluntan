package com.dc.bbsluntan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.bbsluntan.common.R;
import com.dc.bbsluntan.entity.TopicEntity;
import com.dc.bbsluntan.entity.UserEntity;

import java.util.List;

/**
 * @author DengChao
 */

public interface TopicService extends IService<TopicEntity> {

    public TopicEntity selectTopicAndReply(Long topicId);
    public List<TopicEntity> listAndReply(Long sectionId);
}
