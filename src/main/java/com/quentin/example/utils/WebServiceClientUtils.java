package com.quentin.example.utils;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 11:16 2018/1/4
 * @Version 1.0
 */
public class WebServiceClientUtils {
    private static Logger logger = LoggerFactory.getLogger(WebServiceClientUtils.class);

    /**
     * 执行调用webservice服务
     *
     * @param url            webservice地址
     * @param namespace      targetNamespace
     * @param bindingNameStr binding
     * @param methodName     调用方法名
     * @param params         参数对象
     * @Author: guoqun.yang
     * @Date: 2018/1/4 10:49
     * @version 1.0
     */
    public static Object executeService(String url, String namespace, String bindingNameStr, String methodName, Object params) {
        Object[] result = new Object[0];
        try {
            // 创建动态客户端
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            // 创建客户端连接
            Client client = dcf.createClient(url);
            Endpoint endpoint = client.getEndpoint();

            // Make use of CXF service model to introspect the existing WSDL
            ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
            // 创建QName来指定NameSpace和要调用的service(bingdingName)
            QName bindingName = new QName(namespace, bindingNameStr);
            BindingInfo binding = serviceInfo.getBinding(bindingName);
            // 创建QName来指定NameSpace和要调用的方法 Operation
            QName opName = new QName(namespace, methodName);
            BindingOperationInfo boi = binding.getOperation(opName);
            BindingMessageInfo inputMessageInfo;
            if (!boi.isUnwrapped()) {
                inputMessageInfo = boi.getWrappedOperation().getInput();
            } else {
                inputMessageInfo = boi.getUnwrappedOperation().getInput();
            }

            List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
            MessagePartInfo partInfo = parts.get(0); // Input class is serviceBeanMessage

            // 获取入参对象
            Class<?> orderClass = partInfo.getTypeClass();
            Object orderObject = orderClass.newInstance();

            getKeyAndValue(params, orderObject);

            // 调用远程方法并打印结果
            result = client.invoke(opName, orderObject);
            System.out.println("请求结果：" + result[0]);
        } catch (Exception e) {
            logger.error("TestUtils.executeService请求异常：", e);
        }
        return result[0];
    }


    /**
     * 根据入参构造出参对象
     *
     * @param inObj  转化入参
     * @param outObj 出参
     * @Author: guoqun.yang
     * @Date: 2018/1/4 9:43
     * @version 1.0
     */
    private static void getKeyAndValue(Object inObj, Object outObj) {
        // 得到类对象
        Class userCla = inObj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            // 设置些属性是可以访问的
            f.setAccessible(true);
            Object val;
            try {
                val = f.get(inObj);
                // 构造参数对象
                PropertyDescriptor custProperty = new PropertyDescriptor(f.getName(), outObj.getClass());
                custProperty.getWriteMethod().invoke(outObj, val);
            } catch (IllegalAccessException e) {
                logger.error("TestUtils.getKeyAndValue安全权限异常:", e);
            } catch (IntrospectionException e) {
                logger.error("TestUtils.getKeyAndValue内省异常:", e);
            } catch (InvocationTargetException e) {
                logger.error("TestUtils.getKeyAndValue目标异常:", e);
            }
        }
    }
}
