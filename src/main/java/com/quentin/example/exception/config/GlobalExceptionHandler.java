package com.quentin.example.exception.config;

import com.quentin.example.exception.MyException;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常统一处理器（全局异常和自定义异常）
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 16:06 2018/1/19
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error/error_nullpoint");
        return mav;
    }*/

    /**
     * 全局异常处理页面路径
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/26 16:17
     * @version 1.0
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
                configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
                configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
                configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
                configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
            }
        };
    }

    /**
     * 自定义异常返回JSON
     *
     * @param req
     * @param e
     * @Author: guoqun.yang
     * @Date: 2018/1/26 16:18
     * @version 1.0
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}

