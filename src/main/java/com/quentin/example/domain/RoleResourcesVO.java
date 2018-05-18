package com.quentin.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色资源实体
 *
 * @Author Created by guoqun.yang
 * @Date Created in 15:47 2018/2/1
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleResourcesVO {
    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 资源ID
     */
    private Integer resourcesId;

}