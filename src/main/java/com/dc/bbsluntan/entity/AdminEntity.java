package com.dc.bbsluntan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DengChao
 */
@Data
@TableName("bbs_admin")
public class AdminEntity implements Serializable {
    private static final long serialVersionUID = -626065394882L;
    @TableId(type = IdType.AUTO)
    private Integer adminId;
    private String adminName;
    private String adminPassword;
}
