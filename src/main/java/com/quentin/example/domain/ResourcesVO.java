package com.quentin.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源实体
 *
 * @Author Created by guoqun.yang
 * @Date Created in 15:47 2018/2/1
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResourcesVO {
    /**
     * 资源ID
     */
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源URL
     */
    private String resUrl;

    /**
     * 资源类型 1:菜单    2：按钮
     */
    private Integer type;

    /**
     * 父资源
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

}