package com.dc.bbsluntan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dc.bbsluntan.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DengChao
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
