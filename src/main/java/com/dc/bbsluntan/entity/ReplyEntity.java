package com.dc.bbsluntan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private String replyEmotion;
    private String replyTopic;
    private String replyContents;
    private LocalDateTime repplyTime;
    private Integer replyClickCount;
}
