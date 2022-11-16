package com.dc.bbsluntan.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author DengChao
 */
@Data
@TableName("bbs_section")
public class SectionEntity implements Serializable {
    private static final long serialVersionUID = -6260921332394882L;


    @TableId(type = IdType.AUTO)
    private Long sectionId;
    private String sectionName;
    private Long sectionMasterId;
    private String sectionStatement;
    private Integer sectionClickCount;
    private Integer sectionTopicCount;



    @TableField(exist = false)
    List<TopicEntity> topic;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
