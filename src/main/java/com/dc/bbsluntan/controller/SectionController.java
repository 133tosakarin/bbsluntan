package com.dc.bbsluntan.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.bbsluntan.common.R;
import com.dc.bbsluntan.entity.SectionEntity;
import com.dc.bbsluntan.entity.TopicEntity;
import com.dc.bbsluntan.service.SectionService;
import com.dc.bbsluntan.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DengChao
 */
@RestController
@RequestMapping("/section")
public class SectionController {
    @Autowired
    private SectionService sectionService;
    @Autowired
    private TopicService topicService;

    @GetMapping("/listBySection")
    public R<SectionEntity> listBySection(Long sectionId){
        LambdaQueryWrapper<TopicEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TopicEntity::getSectionId,sectionId);
        List<TopicEntity> topics = topicService.list(wrapper);
        SectionEntity sectionServiceById = sectionService.getById(sectionId);
        sectionServiceById.setTopic(topics);
        return R.success(sectionServiceById);
    }
}
