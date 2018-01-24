package com.quentin.example.utils;

import java.math.BigDecimal;

/**
 * 算数运算
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
public class MathUtils {
    /**
     * 精确的加法运算
     *
     * @param v1        被加数
     * @param v2        加数
     * @param precision
     * @return 两个参数的和
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:22
     * @version 1.0
     */
    public static double add(double v1, double v2, int precision) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确的减法运算
     *
     * @param v1        被减数
     * @param v2        减数
     * @param precision
     * @return 两个参数的差
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:22
     * @version 1.0
     */
    public static double sub(double v1, double v2, int precision) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确的乘法运算
     *
     * @param v1        被乘数
     * @param v2        乘数
     * @param precision
     * @return 两个参数的积
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:22
     * @version 1.0
     */
    public static double mul(double v1, double v2, int precision) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * （相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1        被除数
     * @param v2        除数
     * @param precision 表示表示需要精确到小数点以后几位
     * @return 两个参数的商
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:22
     * @version 1.0
     */
    public static double div(double v1, double v2, int precision) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确的小数位四舍五入处理
     *
     * @param v         需要四舍五入的数字
     * @param precision 小数点后保留几位
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:25
     * @version 1.0
     */
    public static double round(double v, int precision) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
