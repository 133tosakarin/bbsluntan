package com.dc.bbsluntan.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.bbsluntan.entity.SectionEntity;
import com.dc.bbsluntan.entity.TopicEntity;
import com.dc.bbsluntan.mapper.SectionMapper;
import com.dc.bbsluntan.service.SectionService;
import com.dc.bbsluntan.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DengChao
 */
@Service
public class SectionServiceImpl extends ServiceImpl<SectionMapper, SectionEntity> implements SectionService {

    @Autowired
    private TopicService topicService;
    @Override
    public SectionEntity selectBySectionIdAndTopic(Long sectionId) {
        SectionEntity section = getById(sectionId);
        section.setTopic(topicService.listAndReply(sectionId));
        return section;
    }
}
