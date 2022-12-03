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
@TableName("bbs_reply")
public class ReplyEntity implements Serializable {
    private static final long serialVersionUID = -6260961652L;


    @TableId(type = IdType.AUTO)
    private Long replyId;
    private Long topicId;
    private Long userId;
    private Long replyFloor;//保存回复reply的id
    private String replyEmotion;
    private String replyContents;
    private Integer replyClickCount;

    @TableField(exist = false)
    List<ReplyEntity> m_replys;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
