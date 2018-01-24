package com.quentin.example.web.controller;

import com.quentin.example.domain.OptEwbVO;
import com.quentin.example.domain.mapper.OptEwbVOMapper;
import com.quentin.example.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 16:06 2018/1/19
 * @Version 1.0
 */
@Controller
@RestController
@RequestMapping("/ewb")
@Api(value = "这个好像没用", description = "运单")
public class EwbController {

    @Autowired
    private OptEwbVOMapper optEwbVOMapper;

    @ApiOperation(value = "获取运单数据", notes = "获取运单数据")
    @ApiImplicitParam(name = "ewbNo", value = "运单号", required = true, dataType = "String" ,paramType = "query")
    @RequestMapping(value = "/getEwb", method = RequestMethod.GET)
    public String getEwbInfo(@RequestParam String ewbNo) {
        OptEwbVO optEwbVO = optEwbVOMapper.selectByEwbNo(ewbNo);
        if (null != optEwbVO) {
            return optEwbVO.toString();
        }
        System.out.println("adasfasdf");
        return "";
    }

    @ApiOperation(value = "获取运单数据展示", notes = "获取运单数据展示", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ewbNo", value = "运单号", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/showEwb", method = RequestMethod.GET)
    public ModelAndView ewbPage(@RequestParam String ewbNo) {
        OptEwbVO optEwbVO = optEwbVOMapper.selectByEwbNo(ewbNo);
        ModelAndView modelAndView = new ModelAndView();
        if (null != optEwbVO) {
            modelAndView.addObject("ewb", optEwbVO);
            modelAndView.setViewName("quentin_index");
        }
        return modelAndView;
    }

    @ApiIgnore//使用该注解忽略这个API
    @ApiOperation(value = "测试远程部署", notes = "测试远程部署")
    @RequestMapping("/showEwbss")
    public String ewbPage() {
        return "ssss12";
    }

    @RequestMapping("/exception")
    public String showMyException() throws MyException {
        throw new MyException("布吉岛发生了什么异常");
    }

}
