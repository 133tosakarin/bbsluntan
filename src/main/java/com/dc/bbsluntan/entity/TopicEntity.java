package com.dc.bbsluntan.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author DengChao
 */
@Data
@TableName("bbs_topic")
public class TopicEntity {
    private static final long serialVersionUID = -6261651394882L;
    @TableId(type = IdType.AUTO)
    private Long topicId;
    private Long sectionId;
    private Long userId;
    private Integer topicReplyCount;
    private String topicEmotion;
    private String topicTitle;
    private String topicContent;
    private Integer topicClickCount;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime
            ;
}
