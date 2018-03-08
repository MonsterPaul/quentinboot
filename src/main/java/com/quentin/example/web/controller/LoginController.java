package com.quentin.example.web.controller;

import com.google.common.collect.Maps;
import com.quentin.example.common.BussinessCode;
import com.quentin.example.common.Constants;
import com.quentin.example.domain.BussinessMsg;
import com.quentin.example.utils.BussinessMsgUtil;
import com.quentin.example.utils.CaptchaUtils;
import com.quentin.example.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录控制器
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 10:42 2018/2/8
 * @Version 1.0
 */
@Controller
@Slf4j
public class LoginController extends BasicContrller {

    @RequestMapping("/")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping("/loginPage")
    ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping("/main")
    ModelAndView mainIndex() {
        ModelAndView modelAndView = new ModelAndView("main_index");
        return modelAndView;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public BussinessMsg login(String username, String password,String code, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        password = MD5Utils.encrypt(username,password);
        Map<String,Object> map = Maps.newHashMap();
        //1.用户名不能为空
        if (StringUtils.isEmpty(username)) {
            log.info("登陆验证失败,原因:用户名不能为空");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_NAME_NULL);
        }
        //2.密码不能为空
        if (StringUtils.isEmpty(password)) {
            log.info("登陆验证失败,原因:密码不能为空");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_PASS_NULL);
        }
        //3.验证码不能为空
        if (StringUtils.isEmpty(code)) {
            log.info("登陆验证失败,原因:验证码不能为空");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_CAPTCHA_NULL);
        }
        //4.验证码输入错误
        String sessionCode = (String) request.getSession().getAttribute(Constants.VALIDATE_CODE);
        if(!code.toLowerCase().equals(sessionCode.toLowerCase())) {
            log.info("登陆验证失败,原因:验证码错误：code:"+code+",sessionCode:"+sessionCode);
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_CAPTCHA_ERROR);
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if (subject.isAuthenticated()) {
                request.getSession().setAttribute("LOGIN_NAME", getCurrentUser());
                map.put("url","main");
                return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_SUCCESS,map);
            }
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_FAIL);
        } catch (IncorrectCredentialsException ice) {
            log.info("登陆验证失败,原因:用户名或密码不匹配");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_FAIL);
        } catch (AccountException e) {
            log.info("登陆验证失败,原因:用户名或密码不匹配");
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_FAIL);
        } catch (Exception e) {
            log.info("登陆验证失败,原因:系统登陆异常", e);
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_LOGIN_ERROR);
        } finally {
            log.info("登陆验证处理结束,用时" + (System.currentTimeMillis() - start) + "毫秒");
        }
    }


    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     *
     * @param request
     * @param response
     * @Author: guoqun.yang
     * @Date: 2018/3/6 20:56
     * @version 1.0
     */
    @GetMapping("/genCaptcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        Object[] obj = CaptchaUtils.getCaptchaImage(150, 50, 35, 50, 500, true, true, CaptchaUtils.ComplexLevel.MEDIUM);
        log.info("本次生成的验证码为[" + obj[1] + "],已存放到HttpSession中");

        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.VALIDATE_CODE, obj[1]);
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        //写给浏览器
        ImageIO.write((BufferedImage) obj[0], "JPEG", response.getOutputStream());
    }
}
