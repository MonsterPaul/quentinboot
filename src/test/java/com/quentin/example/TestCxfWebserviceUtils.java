package com.quentin.example;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quentin.example.config.WSProperties;
import com.quentin.example.utils.ServiceBeanMessage;
import com.quentin.example.utils.WebServiceClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 9:44 2017/12/22
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestCxfWebserviceUtils {
    @Autowired
    private WSProperties wsProperties;


    /**
     * 运单信息
     *
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:05
     * @version 1.0
     */
    public JSONObject putWaybill() {
        JSONObject waybillObject = new JSONObject();
        waybillObject.put("EWB_NO", "301712111009");//运单号
        waybillObject.put("EWB_DATE", "2018-01-22 16:10:27");//寄件日期
        waybillObject.put("PIECE", 1);//运单件数
        waybillObject.put("WEIGHT", 12);//运单重量
        waybillObject.put("CALC_WEIGHT", 200);//运单结算重量
        waybillObject.put("LENGTH", 1);//长
        waybillObject.put("WIDTH", 1);//宽
        waybillObject.put("HIGH", 1);//高
        waybillObject.put("VOL_WEIGHT", 200);//体积重量
        waybillObject.put("VOL", 1);//体积
        waybillObject.put("REWB_NO", "");//回单号
        waybillObject.put("SEND_SITE_ID", 2092);//寄件网点ID
        waybillObject.put("INPUT_SITE_ID", 2092);//录单网点ID
        waybillObject.put("GOODS_TYPE_ID", 85L);//货物类型ID 数据字典（goods_type）
        waybillObject.put("GOODS_EXPLAIN", "说明个屁");//内件说明
        waybillObject.put("PAY_SIDE_ID", 105);//付款方ID 数据字典（pay_side）
        waybillObject.put("PAY_MODE_ID", 104);//支付方式ID 数据字典（pay_mode）
        waybillObject.put("CLASS_ID", 37);//班次ID 数据字典（class）
        waybillObject.put("PICK_GOODS_MODE_ID", 111);//提货方式ID 数据字典（pick_goods_mode）
        waybillObject.put("SMS_MODE_ID", 156);//短信通知方式ID 数据字典（sms_mode）
        waybillObject.put("FREIGHT_CHARGE", "12");//运费
        waybillObject.put("FREIGHT_CURRENCY_ID", 43);//运费币别ID 数据字典（currency）
        waybillObject.put("INSURED_AMOUNT", "13");//保价金额
        waybillObject.put("INSURED_CURRENCY_ID", 43);//保价金额币别ID 数据字典（currency）
        waybillObject.put("COD_CHARGE", "23");//代收货款
        waybillObject.put("COD_CURRENCY_ID", 43);//代收货款币别ID 数据字典（currency）
        waybillObject.put("COD_PAY_MODE_ID", 40);//代收货款支付方式ID 数据字典（cod_pay_mode）
        waybillObject.put("SALE_EMPLOYEE_ID", 7131);//销售业务员ID 员工表
        waybillObject.put("RECEIVE_EMPLOYEE_ID", 7131);//收件业务员ID 员工表
        waybillObject.put("DISPATCH_SITE_ID", 1822);//派件网点ID
        waybillObject.put("DISPATCH_EMPLOYEE_ID", 7137);//派件业务员ID
        waybillObject.put("MAX_EDGE_LENGTH", "13");//最长边长度
        waybillObject.put("STAIRS_TYPE_ID", 322);//楼梯类型ID 数据字典（stairs_type）
        waybillObject.put("CARGO_TYPE_ID", 307);//货物类型ID 数据字典（cargo_type）
        waybillObject.put("SPECIAL_PIECE", 2);//特殊件数
        waybillObject.put("UPSTAIRS_NEW_CHARGE", "23");//新上楼费
        waybillObject.put("IMG_DATE", "2018-01-22 16:10:27");//高扫仪录单图片上传时间
        waybillObject.put("RETURN_DATE", "2018-01-22 16:10:27");//高扫仪录单图片回传时间
        waybillObject.put("FILL_STOREHOUSE", "");//填仓 0非填仓 1 填仓
        waybillObject.put("EC_ID", 63);//电商ID 数据字典（ec）
        waybillObject.put("EC_WAREHOUSE_ID", 1);//电商仓库ID from HS_BASIC_EC_WAREHOUSE
        waybillObject.put("ORDER_NO", "ANE20150315000013");//电商订单号
        waybillObject.put("REMARK", "cscsscscs");//备注
        waybillObject.put("ARRIVE_OPT_AMOUNT", 35.00D);//到付操作费
        waybillObject.put("KFBXF_CHARGE", 34.00D);//客户保险费
        waybillObject.put("FUEL_ADD_CHARGE", 33.00D);//燃油附加费
        waybillObject.put("PICKUP_CHARGE", 66.00D);//提货费
        waybillObject.put("EC_IN_CHARGE", 34.00D);//电商入仓费
        waybillObject.put("KFSLF_CHARGE", 45.00D);//客户上楼费
        waybillObject.put("SERVICE_DIP_FLAG", 0);//服务商派送标志（天猫家装）0：否 1：是
        waybillObject.put("SERVICE_FIX_FLAG", 0);//服务商安装标志（天猫家装）0：否 1：是
        waybillObject.put("SHIPPING_METHOD", 180);//送货方式(179_送货上楼,180_送货上门 数据字典（shipping_method）
        waybillObject.put("DATA_SOURCE", 1);//数据来源（1：普通录单（包括加盟和直营） 2：批量录单 3：代收货款录单 4：拍照录单 5：揽件转运单 6：集中录单 7：360录单）
        waybillObject.put("GOODS_CATEGORY", 1);//物类别(1_易碎品)
        waybillObject.put("CREATED_BY", 6288);//创建人ID
        waybillObject.put("MAPPINGVERSION", 1);//绘图版本
        waybillObject.put("CAN_GIVE_OUT", "Y");//是否可外发(Y 为可外发)
        waybillObject.put("ISJZ", "Y");//是否家装;Y为是，默认为空
        waybillObject.put("MATCH_TYPE", 1);//匹配类型 1：地址库匹配 2：关键字匹配 3：地图匹配
        waybillObject.put("RETURN_FLAG", 0);//退件标识
        waybillObject.put("OLD_EWB_NO", "");//原退件单号
        waybillObject.put("PIC_EWB_ID", null);//面单图片ID
        waybillObject.put("COUPON", "");//优惠券号
        waybillObject.put("ADDRESS2", "");//GIS匹配地址
        return waybillObject;
    }

    /**
     * 网点-开单匹配数据报表
     *
     * @param waybillObject 运单JSON
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:05
     * @version 1.0
     */
    public JSONObject putReport(JSONObject waybillObject) {
        JSONObject reportJson = new JSONObject();
        reportJson.put("EWB_NO", "301712111009");//运单号
        reportJson.put("DISPATCH_SITE_ID", 1822);//派件网点ID
        reportJson.put("GIS_SITE_ID", 1822);//GIS 解析网点
        reportJson.put("MEMORY_SITE_ID", 1822);//记忆网点
        reportJson.put("HAND_SITE_ID", 1822);//手选网点
        waybillObject.put("optEwbReport", reportJson);//网点-开单匹配数据报表
        return waybillObject;
    }

    /**
     * 录单地址匹配失败记录
     *
     * @param waybillObject 运单JSON
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:05
     * @version 1.0
     */
    public JSONObject putGis(JSONObject waybillObject) {
        JSONObject gisJson = new JSONObject();
        gisJson.put("EWB_NO", "301712111009");//运单号
        gisJson.put("CUSTOMER_NAME", "Quentin");//收件客户名称
        gisJson.put("ADDRESS", "湖南长沙岳麓区");//收件地址
        gisJson.put("SEND_SITE_ID", 2092);//寄件网点
        gisJson.put("GIS_DISPATCH_SITE_ID", 1822);//GIS地图返回目的网点
        gisJson.put("REAL_DISPATCH_SITE_ID", 1822);//实际录单目的网点
        waybillObject.put("ewbAddress", gisJson);//录单地址匹配失败记录
        return waybillObject;
    }

    /**
     * 面单打印记录
     *
     * @param waybillObject 运单JSON
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:05
     * @version 1.0
     */
    public JSONObject putPrint(JSONObject waybillObject) {
        JSONArray printList = new JSONArray();
        JSONObject printJson = new JSONObject();
        printJson.put("PRINT_EWB_TYPE", 0);
        printJson.put("EWB_NO", "301712111009");//运单号
        printJson.put("PRINT_SITE_ID", 2092);//打印网点ID
        printJson.put("PRINT_PIECE", "1");//打印件数
        printJson.put("PRINT_SERVICES_TYPE_ID", 0L);//打印产品类型
        printJson.put("PRINT_PICK_GOODS_MODE_ID", 0L);//打印服务方式
        printJson.put("CREATED_BY", 0L);//打印人
        printJson.put("CREATED_TIME", "2018-01-22 16:10:27");//打印时间
        printList.add(printJson);
        waybillObject.put("printList", printList);//面单打印记录
        return waybillObject;
    }

    /**
     * 客户国家ID
     *
     * @param waybillObject 运单JSON
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:05
     * @version 1.0
     */
    public JSONObject putCustoms(JSONObject waybillObject) {
        JSONArray customerinfoArray = new JSONArray();
        JSONObject customsJson = new JSONObject();
        customsJson.put("CUSTOMER_COUNTRY_ID", 0L);
        customerinfoArray.add(customsJson);
        waybillObject.put("customerinfo", customerinfoArray);
        return waybillObject;
    }

    /**
     * 运单明细
     *
     * @param waybillObject 运单JSON
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:05
     * @version 1.0
     */
    public JSONObject putEwbDetail(JSONObject waybillObject) {
        JSONObject ewbDetail = new JSONObject();
        ewbDetail.put("PRODUCT_TYPE", 24);//产品类型 数据字典（services_type）
        ewbDetail.put("EWB_TYPE", 1);//运单类型 1：加盟运单 2：直营运单
        ewbDetail.put("EEWBS_FLAG", 1);//电子面单标志（0：否  1：是）
        ewbDetail.put("ARRIVED_SMS_FLAG", 0);//到件短信通知寄件人（0：不通知  1：通知）
        ewbDetail.put("PACKAGE_TYPE", "盒子啦");//包裹类型
        ewbDetail.put("GOODS_NAME", "手机");//物品名称
        ewbDetail.put("VOL_DETAIL", "");//体积明细
        ewbDetail.put("TOTAL_AMOUNT", 500.00D);//实收总金额
        ewbDetail.put("BILL_CHARGE", 12.00D);//制单费
        ewbDetail.put("COUNT_CHARGE", 12.00D);//清点费
        ewbDetail.put("UPSTAIRS_CHARGE", 12.00D);//上下楼费
        ewbDetail.put("WAREHOUSE_CHARGE", 12.00D);//进仓服务费
        ewbDetail.put("OTHER_CHARGE", 12.00D);//其它费用
        ewbDetail.put("DELIVERY_CHARGE", 14.00D);//送货费
        ewbDetail.put("INSTALLER_CHARGE", 15.00D);//安装费
        ewbDetail.put("REFUND_CHARGE", 13.00D);//返款到付
        ewbDetail.put("DISPATCH_SMS_FLAG",0);//派件短信通知收件人（0：不通知  1：通知）
        ewbDetail.put("FUEL_CHARGE", 12.00D);//燃油费
        ewbDetail.put("PREMIUM_CHARGE", 12.00D);//保费
        ewbDetail.put("FREIGHT_REMARK", "don't know");//运费修改原因
        ewbDetail.put("RETURN_TIME", "2018-01-22 16:10:27");//返款时效
        ewbDetail.put("RETURN_ACCOUNT_TYPE", "0");//返款类型（0-表示没有，1-表示客户，2-表示网点）
        ewbDetail.put("COD_BANK_NAME", "");//返代收货款到寄件账户开户银行名称
        ewbDetail.put("COD_ACCOUNT_CODE", "");//返代收货款到寄件账户开户行账号
        ewbDetail.put("COD_ACCOUNT_NAME", "");//返代收货款到寄件账户开户行户名
        ewbDetail.put("COD_BANKAREA", "");//返代收货款到寄件账户银行地区
        ewbDetail.put("COD_PROVINCE_NAME", "");//返代收货款到寄件账户省份名称
        ewbDetail.put("COD_CITY_NAME", "");//返代收货款到寄件账户城市名称
        ewbDetail.put("COD_COMBINENUM", "");//返代收货款到寄件账户联行号
        ewbDetail.put("COD_ACCOUNT_TYPE", 1);//账户类型(1：个人  2：事业单位  3：公司)
        ewbDetail.put("UPSTAIRS_TYPE", 1);//1：标准件上下楼	2：超标件上下楼	3：特殊件上下楼
        //=========华丽分割线=========
        //安装费-物品类型明细
        JSONArray goodsArray = new JSONArray();
        JSONObject goodsJson = new JSONObject();
        goodsJson.put("GOODS_ID", 80142);//安装费-物品类型ID HS_FIN_GOODS
        goodsJson.put("GOODS_PIECE", 1);//计件数量
        goodsJson.put("GOODS_UNIT_PRICE", 12.00D);//单价
        goodsJson.put("GOODS_LOWEST_QUOTE", 1.00D);//最低报价
        goodsJson.put("QUANTITY", 1);//数量
        goodsJson.put("PRICE", 12.00D);//价格
        goodsArray.add(goodsJson);
        //=========华丽分割线=========
        ewbDetail.put("goodsDetail", goodsArray);//安装费-物品类型明细
        waybillObject.put("ewbDetail", ewbDetail);
        return waybillObject;
    }

    /**
     * 费用明细
     *
     * @param waybillObject 运单JSON
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:05
     * @version 1.0
     */
    public JSONObject putChargeInfo(JSONObject waybillObject) {
        JSONObject chargeInfo = new JSONObject();
        chargeInfo.put("cusFreightAmount", 1.00D);//客户报价
        chargeInfo.put("bonusPoolFee", 1.00D);//红利基金
        chargeInfo.put("transAmount", 1.00D);
        chargeInfo.put("dispatchAmount", 1.00D);
        chargeInfo.put("overWeightAmount", 1.00D);
        chargeInfo.put("payDispatcherAmount", 2.00D);
        chargeInfo.put("payOverWeightAmount", 3.00D);
        chargeInfo.put("optAmount", 4.00D);
        chargeInfo.put("fuelAmount", 4.00D);
        chargeInfo.put("meetingAmount", 4.00D);
        chargeInfo.put("meetingSubsidyAmount", 4.00D);
        chargeInfo.put("siteMeetingAmount", 4.00D);//网点会务费
        chargeInfo.put("paySendOperationAmount", 2.00D);
        chargeInfo.put("reServerSubAmount", 5.00D);//收服务补贴
        chargeInfo.put("arriveOptAmount", 4.00D);
        chargeInfo.put("payPremiumAmount", 5.00D);
        chargeInfo.put("receiptAmount", 5.00D);
        chargeInfo.put("collectAmount", 1.00D);
        chargeInfo.put("payDispatchCollectAmount", 5.00D);
        chargeInfo.put("codOptAmount", 5.00D);
        chargeInfo.put("siteTransAmount", 5.00D);
        chargeInfo.put("siteReDispachFee", 1.00D);
        chargeInfo.put("sitePayDispatchFee", 5.00D);
        chargeInfo.put("siteDisArrivePayFee", 5.00D);
        chargeInfo.put("premiumAmount", 4.00D);
        chargeInfo.put("siteReArrivePayFee", 4.00D);
        chargeInfo.put("disCharge", 5.00D);
        chargeInfo.put("serviceCharge", 1.00D);
        chargeInfo.put("stairsAmount", 1.00D);//上楼费
        //特殊费用
        chargeInfo.put("dobCleanCharge",0.0);//清点费
        chargeInfo.put("dobTurnOrAgencyCharge", 0.0);//转退件或代取件费
        chargeInfo.put("dobStoreRoomCharge", 0.0);//进仓服务费
        chargeInfo.put("dobSpecialAreaCharge", 0.0);//特殊区域加收费
        chargeInfo.put("dobSpecialCargoUpCharge", 0.0);//特殊货物上楼费
        chargeInfo.put("dobOverCargoCarCharge", 0.0);//超标货物请车费
        chargeInfo.put("dobDeadAreaCargoCarCharge", 0.0);//超标货物请车费
        chargeInfo.put("dobOverLimitCargoUpCharge", 0.0);//超标货物上楼费
        chargeInfo.put("dobHurryCargoCarCharge", 0.0);//加急货物请车费
        waybillObject.put("chargeInfo", chargeInfo);
        return waybillObject;
    }

    /**
     * 子单信息
     *
     * @param waybillObject
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:05
     * @version 1.0
     */
    public JSONObject putHewb(JSONObject waybillObject) {
        JSONArray hewbArray = new JSONArray();
        JSONObject hewbJson = new JSONObject();
        hewbJson.put("EWB_NO", "301712111009");
        hewbJson.put("HEWB_NO", "30171211100900010001");
        hewbArray.add(hewbJson);
        waybillObject.put("children", hewbArray);
        return waybillObject;
    }

    /**
     * 寄件客户信息
     *
     * @param sendInfoObj 寄件信息
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:28
     * @version 1.0
     */
    public JSONObject putSendCustomerInfo(JSONObject sendInfoObj) {
        JSONObject sendCustInfoObj = new JSONObject();
        sendCustInfoObj.put("CUSTOMER_NAME", "Quentin");
        sendCustInfoObj.put("CUSTOMER_NAME_ALL", "来自星星的Quentin");
        sendCustInfoObj.put("PHONE", "15616547548");
        sendCustInfoObj.put("PHONE_SMS", "15616547548");
        sendCustInfoObj.put("E_MAIL", "qundongni@qq.com");
        sendCustInfoObj.put("FAX", "");
        sendCustInfoObj.put("POSTCODE", "");
        sendCustInfoObj.put("SALES_EMPLOYEE_ID", 0L);
        sendCustInfoObj.put("REC_EMPLOYEE_ID", 0L);
        sendCustInfoObj.put("DISP_EMPLOYEE_ID", 0L);
        sendCustInfoObj.put("CREATED_BY", 0L);
        sendInfoObj.put("info", sendCustInfoObj);
        return sendInfoObj;
    }

    /**
     * 寄件地址信息
     *
     * @param sendInfoObj 寄件信息
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:28
     * @version 1.0
     */
    public JSONObject putSendAddress(JSONObject sendInfoObj) {
        JSONObject sendCustAddressObj = new JSONObject();
        sendCustAddressObj.put("CUSTOMER_ID", 0L);
        sendCustAddressObj.put("COUNTRY_ID", 0L);
        sendCustAddressObj.put("PROVINCE_ID", 0L);
        sendCustAddressObj.put("CITY_ID", 0L);
        sendCustAddressObj.put("COUNTY_ID", 0L);
        sendCustAddressObj.put("ADDRESS", "湖南长沙岳麓区。。。");
        sendCustAddressObj.put("PHONE_SMS", "15616547548");
        sendCustAddressObj.put("PHONE", "");
        sendCustAddressObj.put("E_MAIL", "");
        sendCustAddressObj.put("POSTCODE", "");
        sendCustAddressObj.put("BL_DEFAULT", 0);
        sendCustAddressObj.put("CREATED_BY", 0L);
        sendCustAddressObj.put("IS_AUDIT", 0);
        sendInfoObj.put("addr", sendCustAddressObj);
        return sendInfoObj;
    }

    /**
     * 收件客户信息
     *
     * @param receiveInfoObj 收件信息
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:28
     * @version 1.0
     */
    public JSONObject putReceiveCustomerInfo(JSONObject receiveInfoObj) {
        JSONObject receiveCustInfoObj = new JSONObject();
        receiveCustInfoObj.put("CUSTOMER_NAME", "MJ");
        receiveCustInfoObj.put("CUSTOMER_NAME_ALL", "some body that i use to know");
        receiveCustInfoObj.put("PHONE", "14754145411");
        receiveCustInfoObj.put("PHONE_SMS", "14754145411");
        receiveCustInfoObj.put("E_MAIL", "");
        receiveCustInfoObj.put("FAX", "");
        receiveCustInfoObj.put("POSTCODE", "");
        receiveCustInfoObj.put("SALES_EMPLOYEE_ID", 0L);
        receiveCustInfoObj.put("REC_EMPLOYEE_ID", 0L);
        receiveCustInfoObj.put("DISP_EMPLOYEE_ID", 0L);
        receiveCustInfoObj.put("CREATED_BY", 0L);
        receiveInfoObj.put("info", receiveCustInfoObj);
        return receiveInfoObj;
    }

    /**
     * 收件地址信息
     *
     * @param receiveInfoObj 收件信息
     * @Author: guoqun.yang
     * @Date: 2018/1/5 10:28
     * @version 1.0
     */
    public JSONObject putReceiveAddress(JSONObject receiveInfoObj) {
        JSONObject receiveCustAddressObj = new JSONObject();
        receiveCustAddressObj.put("CUSTOMER_ID", 0L);
        receiveCustAddressObj.put("COUNTRY_ID", 0L);
        receiveCustAddressObj.put("PROVINCE_ID", 0L);
        receiveCustAddressObj.put("CITY_ID", 0L);
        receiveCustAddressObj.put("COUNTY_ID", 0L);
        receiveCustAddressObj.put("ADDRESS", "湖南湘西吉首，，，，");
        receiveCustAddressObj.put("PHONE_SMS", "18474745414");
        receiveCustAddressObj.put("PHONE", "");
        receiveCustAddressObj.put("E_MAIL", "");
        receiveCustAddressObj.put("POSTCODE", "");
        receiveCustAddressObj.put("BL_DEFAULT", 0);
        receiveCustAddressObj.put("CREATED_BY", 0L);
        receiveCustAddressObj.put("IS_AUDIT", 0);
        receiveInfoObj.put("addr", receiveCustAddressObj);
        return receiveInfoObj;
    }

    public String getParams() {
        JSONObject resultObj = new JSONObject();
        JSONObject resultArrayObject = new JSONObject();
        JSONArray waybillListArray = new JSONArray();
        JSONObject object = new JSONObject();
        //---------------华丽分割线start------------------
        //运单信息
        JSONObject waybillobj = putWaybill();
        //putReport(waybillobj);
        //putGis(waybillobj);
        //putPrint(waybillobj);
        putCustoms(waybillobj);
        putEwbDetail(waybillobj);
        putChargeInfo(waybillobj);
        putHewb(waybillobj);
        object.put("waybill", waybillobj);
        //---------------华丽分割线end------------------

        //---------------华丽分割线start------------------
        //寄件客户信息
        JSONObject sendInfoObj = putSendCustomerInfo(new JSONObject());
        sendInfoObj = putSendAddress(sendInfoObj);
        object.put("sendcustomerinfos", sendInfoObj);
        //---------------华丽分割线end------------------

        //---------------华丽分割线start------------------
        //收件客户信息
        JSONObject receiveInfoObj = putReceiveCustomerInfo(new JSONObject());
        receiveInfoObj = putReceiveAddress(receiveInfoObj);
        object.put("recvcustomerinfos", receiveInfoObj);
        //---------------华丽分割线end------------------
        waybillListArray.add(object);
        resultArrayObject.put("waybilllist", waybillListArray);
        resultObj.put("result", resultArrayObject);
        return resultObj.toJSONString();
    }

    @Test
    public void cxfWs() {
        String url = "http://lzq657877648.vicp.cc/services/NE8Service?wsdl";
        String nameSpace = "http://web.operation.ne.chengfeng.com/";
        String bindingNameStr = "NE8ServiceServiceSoapBinding";
        String method = "callNE8LogicToString";
        String str = getParams();
        ServiceBeanMessage serviceBeanMessage = new ServiceBeanMessage();
        serviceBeanMessage.setLoginUser("aneAdmin");
        serviceBeanMessage.setLoginPwd("ane654321");
        serviceBeanMessage.setIp("192.168.1.8");
        serviceBeanMessage.setServerCode("200101");
        serviceBeanMessage.setParams("");
        serviceBeanMessage.setStates("1");

        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            // 使用默认缓冲区大小创建新的输出流
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            // 将 b.length 个字节写入此输出流
            gzip.write(str.getBytes("utf-8"));
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serviceBeanMessage.setScanParams(out.toByteArray());
        //调用WebService
        WebServiceClientUtils.executeService(url, nameSpace, bindingNameStr, method, serviceBeanMessage);
    }

    @Test
    public void testVolWeight() {
        JSONObject paramObj = new JSONObject();
        paramObj.put("date","2018-01-23 09:41:00");
        paramObj.put("weight","12.0");
        paramObj.put("sendSiteId","4001");
        paramObj.put("dispatchSiteId","3662");
        paramObj.put("vol","23.0");
        String str = paramObj.toJSONString();
        ServiceBeanMessage serviceBeanMessage = new ServiceBeanMessage();
        serviceBeanMessage.setLoginUser(wsProperties.getLoginUser());
        serviceBeanMessage.setLoginPwd(wsProperties.getLoginPwd());
        serviceBeanMessage.setIp(wsProperties.getIp());
        serviceBeanMessage.setServerCode("102101");
        serviceBeanMessage.setParams(str);
        serviceBeanMessage.setStates("1");

        serviceBeanMessage.setScanParams(null);
        //调用WebService
        WebServiceClientUtils.executeService(wsProperties.getUrl(), wsProperties.getNameSpace(), wsProperties.getBindingName(), wsProperties.getMethod(), serviceBeanMessage);

    }

    @Test
    public void testProperties() {
        log.info(wsProperties.getUrl());
    }


}
