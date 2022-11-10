package com.dc.bbsluntan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
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
}
