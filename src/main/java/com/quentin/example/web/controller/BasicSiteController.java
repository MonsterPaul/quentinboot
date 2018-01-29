package com.quentin.example.web.controller;

import com.quentin.example.domain.BasicSiteVO;
import com.quentin.example.service.IBasicSiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 10:34 2018/1/29
 * @Version 1.0
 */
@Controller
@RestController
@RequestMapping(value = "/site")
@Api
public class BasicSiteController {
    @Autowired
    private IBasicSiteService basicSiteService;

    @RequestMapping(value = "/addSite",method = RequestMethod.GET)
    public void addSite() {
        BasicSiteVO siteVO = new BasicSiteVO();
        siteVO.setSiteId(19773);
        siteVO.setSiteCode("433122");
        siteVO.setSiteName("吉首解放岩");
        siteVO.setSitePinyin("JSJFY");
        siteVO.setSiteType("二级加盟网点");
        siteVO.setParentSiteCode("7313027");
        siteVO.setRdStatus(1);
        siteVO.setLastTime(new Date());
        siteVO.setCreator(null);
        siteVO.setCreateTime(new Date());
        siteVO.setUpdator(null);
        siteVO.setUpdateTime(new Date());
        siteVO.setIsDeleted(0);
        try {
            int success = basicSiteService.insertBasicSite(siteVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("执行完喽");
    }

    @ApiIgnore
    @RequestMapping(value = "/delete")
    public void deleteBasicSite() {
        Long id = 19773L;
        try {
            basicSiteService.deleteBasicSiteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable long id) {
        int i = 0;
        try {
            i = basicSiteService.deleteBasicSiteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i > 0 ? "success" : "failed";
    }

}
