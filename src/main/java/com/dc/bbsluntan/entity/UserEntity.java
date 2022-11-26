package com.dc.bbsluntan.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @author DengChao
 */
@Data
@TableName("bbs_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -6260961651394882L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userAccountId;
    private String username;
    private String password;
    private String userEmail;
    private Integer userSex;
    private String userClass;
    private String userStatement;
    private Integer userStatus;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
