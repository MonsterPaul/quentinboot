package com.quentin.example.utils;

/**
 * 操作系统判断
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
public class SystemUtils {

    private static final String osname = System.getProperty("os.name").toLowerCase();

    /**
     * 判断是否是Linux系统
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/18 11:33
     * @version 1.0
     */
    public static boolean isLinux() {
        if (osname.indexOf("linux") >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是windows系统
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/18 11:33
     * @version 1.0
     */
    public static boolean isWindows() {
        if (osname.indexOf("windows") >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是mac系统
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/18 11:33
     * @version 1.0
     */
    public static boolean isMac() {
        if (osname.indexOf("mac") >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 返回系统名称
     *
     * @param
     * @Author: guoqun.yang
     * @Date: 2018/1/18 11:33
     * @version 1.0
     */
    public static String getOSName() {
        return osname;
    }

}
