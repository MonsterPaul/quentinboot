package com.quentin.example.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MenuVO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_id
     *
     * @mbggenerated
     */
    private Long menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.parent_id
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.perms
     *
     * @mbggenerated
     */
    private String perms;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.icon
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.order_num
     *
     * @mbggenerated
     */
    private Integer orderNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.gmt_create
     *
     * @mbggenerated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.gmt_modified
     *
     * @mbggenerated
     */
    private Date gmtModified;


}