package com.dc.bbsluntan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.bbsluntan.entity.ReplyEntity;
import com.dc.bbsluntan.entity.UserEntity;

import java.util.List;

/**
 * @author DengChao
 */

public interface ReplyService extends IService<ReplyEntity> {
    ReplyEntity getReplyAndFloor(Long replyId);
}
