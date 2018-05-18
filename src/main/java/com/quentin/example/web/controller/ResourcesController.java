package com.quentin.example.web.controller;

import com.github.pagehelper.PageInfo;
import com.quentin.example.domain.ResourcesVO;
import com.quentin.example.service.IResourcesService;
import com.quentin.example.service.IShiroService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源控制器
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 10:42 2018/2/8
 * @Version 1.0
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Resource
    private IResourcesService resourcesService;
    @Resource
    private IShiroService shiroService;

    @RequestMapping
    public Map<String, Object> getAll(ResourcesVO resources, String draw,
                                      @RequestParam(required = false, defaultValue = "1") int start,
                                      @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        PageInfo<ResourcesVO> pageInfo = resourcesService.selectByPage(resources, start, length);
        System.out.println("pageInfo.getTotal():" + pageInfo.getTotal());
        map.put("draw", draw);
        map.put("recordsTotal", pageInfo.getTotal());
        map.put("recordsFiltered", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping("/resourcesWithSelected")
    public List<ResourcesVO> resourcesWithSelected(Integer rid) {
        return resourcesService.queryResourcesListWithSelected(rid);
    }

    /**
     * 加载菜单
     *
     * @param
     * @return java.util.List<Resources>
     * @author guoqun.yang
     * @date 2018/4/25 21:01
     * @version 1.0
     */
    @RequestMapping("/loadMenu")
    public List<ResourcesVO> loadMenu() {
        Map<String, Object> map = new HashMap<>();
        Integer userid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        map.put("type", 1);
        map.put("userid", userid);
        List<ResourcesVO> resourcesList = resourcesService.loadUserResources(map);
        return resourcesList;
    }

    /**
     * 添加资源
     *
     * @param resources
     * @return java.lang.String
     * @author guoqun.yang
     * @date 2018/4/25 21:01
     * @version 1.0
     */
    @RequestMapping(value = "/add")
    public String add(ResourcesVO resources) {
        try {
            resourcesService.save(resources);
            //更新权限
            shiroService.updatePermission();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 删除资源
     *
     * @param id
     * @return java.lang.String
     * @author guoqun.yang
     * @date 2018/4/25 21:01
     * @version 1.0
     */
    @RequestMapping(value = "/delete")
    public String delete(Integer id) {
        try {
            resourcesService.delete(id);
            //更新权限
            shiroService.updatePermission();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
