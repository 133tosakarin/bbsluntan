package com.dc.bbsluntan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dc.bbsluntan.entity.TopicEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DengChao
 */
@Mapper
public interface TopicMapper extends BaseMapper<TopicEntity> {


}
