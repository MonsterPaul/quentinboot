package com.quentin.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 14:28 2018/1/25
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "ws")
@Data
public class WSProperties {
    private String url;
    private String nameSpace;
    private String bindingName;
    private String method;
    private String loginUser;
    private String loginPwd;
    private String ip;

}
