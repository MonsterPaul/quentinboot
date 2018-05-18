package com.quentin.example.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 10:34 2018/1/29
 * @Version 1.0
 */
@Controller
@RestController
@RequestMapping(value = "/site")
@Api(value = "这个好像没用", description = "网点")
public class BasicSiteController {

    @ApiIgnore
    @RequestMapping(value = "/delete")
    public void deleteBasicSite() {
        Long id = 19773L;
        try {
            //basicSiteService.deleteBasicSiteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //paramType 有五个可选值 ： path, query, body, header, form
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable long id) {
        int i = 0;
        try {
            //i = basicSiteService.deleteBasicSiteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i > 0 ? "success" : "failed";
    }

}
