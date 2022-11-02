package com.dc.bbsluntan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.bbsluntan.entity.AdminEntity;
import com.dc.bbsluntan.entity.UserEntity;
import com.dc.bbsluntan.mapper.AdminMapper;
import com.dc.bbsluntan.mapper.UserMapper;
import com.dc.bbsluntan.service.AdminService;
import com.dc.bbsluntan.service.UserService;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

/**
 * @author DengChao
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {
}
