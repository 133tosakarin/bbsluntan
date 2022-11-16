package com.dc.bbsluntan.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.bbsluntan.common.R;
import com.dc.bbsluntan.entity.SectionEntity;
import com.dc.bbsluntan.entity.TopicEntity;
import com.dc.bbsluntan.service.SectionService;
import com.dc.bbsluntan.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DengChao
 */
@Slf4j
@RestController
@RequestMapping("/section")
public class SectionController {
    @Autowired
    private SectionService sectionService;
    @Autowired
    private TopicService topicService;

    @GetMapping("/listBySection")
    public R<SectionEntity> listBySection(Long sectionId){
        log.info("查询板块所有帖子");
        SectionEntity sectionEntity = sectionService.selectBySectionIdAndTopic(sectionId);
        return R.success(sectionEntity);
    }
}
