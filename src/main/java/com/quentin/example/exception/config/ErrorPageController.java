package com.quentin.example.exception.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义错误页面控制器
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 16:06 2018/1/19
 * @Version 1.0
 */
@Controller
@RequestMapping("/error")
@EnableConfigurationProperties({ServerProperties.class})
@ApiIgnore
public class ErrorPageController implements ErrorController {

    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * 初始化ExceptionController
     *
     * @param errorAttributes
     */
    @Autowired
    public ErrorPageController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    /**
     * 404异常页面
     *
     * @param request
     * @param response
     * @Author: guoqun.yang
     * @Date: 2018/1/26 15:40
     * @version 1.0
     */
    @RequestMapping(produces = "text/html", value = "404")
    public ModelAndView errorHtml404(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error/error_400", model);
    }

    /**
     * 400异常页面
     *
     * @param request
     * @param response
     * @Author: guoqun.yang
     * @Date: 2018/1/26 15:40
     * @version 1.0
     */
    @RequestMapping(produces = "text/html", value = "400")
    public ModelAndView errorHtml400(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error/error_400", model);
    }

    /**
     * 500异常页面
     *
     * @param request
     * @param response
     * @Author: guoqun.yang
     * @Date: 2018/1/26 15:40
     * @version 1.0
     */
    @RequestMapping(produces = "text/html", value = "500")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error/error_500", model);
    }

    /**
     * 是否包含堆栈
     *
     * @param request  the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @Author: guoqun.yang
     * @Date: 2018/1/26 16:46
     * @version 1.0
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }


    /**
     * 获取错误的信息
     *
     * @param request
     * @Author: guoqun.yang
     * @Date: 2018/1/26 16:33
     * @version 1.0
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

    /**
     * 是否包含trace
     *
     * @param request
     * @Author: guoqun.yang
     * @Date: 2018/1/26 16:33
     * @version 1.0
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    /**
     * 获取错误编码
     *
     * @param request
     * @Author: guoqun.yang
     * @Date: 2018/1/26 16:33
     * @version 1.0
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public String getErrorPath() {
        return "";
    }
}
