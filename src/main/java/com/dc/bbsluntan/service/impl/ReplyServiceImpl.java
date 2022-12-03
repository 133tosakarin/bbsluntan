package com.dc.bbsluntan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.bbsluntan.entity.ReplyEntity;
import com.dc.bbsluntan.entity.UserEntity;
import com.dc.bbsluntan.mapper.ReplyMapper;
import com.dc.bbsluntan.mapper.UserMapper;
import com.dc.bbsluntan.service.ReplyService;
import com.dc.bbsluntan.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DengChao
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, ReplyEntity> implements ReplyService {
    @Override
    public ReplyEntity getReplyAndFloor(Long replyId) {
        LambdaQueryWrapper<ReplyEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReplyEntity::getReplyFloor,replyId);
        List<ReplyEntity> list = this.list(wrapper);
        ReplyEntity reply = getById(replyId);
        reply.setM_replys(list);
        return reply;
    }
}
