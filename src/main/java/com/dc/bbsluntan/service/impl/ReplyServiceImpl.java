package com.dc.bbsluntan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.bbsluntan.entity.ReplyEntity;
import com.dc.bbsluntan.entity.UserEntity;
import com.dc.bbsluntan.mapper.ReplyMapper;
import com.dc.bbsluntan.mapper.UserMapper;
import com.dc.bbsluntan.service.ReplyService;
import com.dc.bbsluntan.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author DengChao
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, ReplyEntity> implements ReplyService {
}
