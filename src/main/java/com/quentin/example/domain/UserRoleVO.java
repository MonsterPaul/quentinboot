package com.quentin.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色实体
 *
 * @Author Created by guoqun.yang
 * @Date Created in 15:47 2018/2/1
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVO {
    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 角色ID
     */
    private Integer roleid;


}