package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {

    @NotNull(groups = Update.class)
    private Integer id;//主键ID

    /**
     * used when check a request,need a @validated in controller to enable
     */
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryName;//分类名称

    /**
     * Default
     */
    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    /**
     * 分组校验的一个组，组在类成员上指定，同时在controller的@Validated上指定
     * 默认分组是Default，分组可以继承
     */
    public interface Add extends Default {

    }

    public interface Update extends Default{

    }
}
