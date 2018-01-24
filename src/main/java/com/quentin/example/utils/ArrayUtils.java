/**
 * 上海蓝鸟集团
 * 上海蓝鸟科技股份有限公司
 * 华东工程中心（无锡）
 * 2015版权所有
 */
package com.quentin.example.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数组工具类
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
public class ArrayUtils {

    /**
     * 判断数组是否为空
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:30
     * @version 1.0
     */
    public static <T> boolean isEmpty(T[] array) {
        if (array == null || array.length == 0) return true;
        return false;
    }

    /**
     * 获取数组长度
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:30
     * @version 1.0
     */
    public static <T> int length(T[] array) {
        if (array == null || array.length == 0) return 0;
        return array.length;
    }

    /**
     * 判断数组是否含有某个元素
     *
     * @param array
     * @param t
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:30
     * @version 1.0
     */
    public static <T> boolean contains(T[] array, T t) {
        if (length(array) == 0) return false;
        for (T _t : array) {
            if (_t == t) return true;
        }
        return false;
    }

    /**
     * 数组转List
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:29
     * @version 1.0
     */
    public static <T> List<T> asList(T[] array) {
        return Arrays.asList(array);
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static int[] sort(int[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static char[] sort(char[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static double[] sort(double[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static float[] sort(float[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array           待排序数组
     * @param caseInsensitive 是否大小写敏感
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:28
     * @version 1.0
     */
    public static String[] sort(String[] array, boolean caseInsensitive) {
        if (caseInsensitive) {
            Arrays.sort(array);
        } else {
            Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
        }
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static byte[] sort(byte[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static short[] sort(short[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static Object[] sort(Object[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static Character[] sort(Character[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static Double[] sort(Double[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static Float[] sort(Float[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static Byte[] sort(Byte[] array) {
        Arrays.sort(array);
        return array;
    }

    /**
     * 排序 min-max
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:27
     * @version 1.0
     */
    public static Short[] sort(Short[] array) {
        Arrays.sort(array);
        return array;
    }

    public static <T> T[] sort(T[] array, Comparator<T> c) {
        Arrays.sort(array, c);
        return array;
    }

    /**
     * 数组转String
     *
     * @param array
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:26
     * @version 1.0
     */
    public static <T> String toString(T[] array) {
        StringBuffer sb = new StringBuffer("");
        if (isEmpty(array)) return sb.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i = 0;
        for (T t : array) {
            if ((t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof Boolean) || (t instanceof Byte) || (t instanceof String) || (t instanceof Character) || (t instanceof Float) || (t instanceof Double) || (t instanceof Date)) {
                if (t instanceof Date) {
                    sb.append((i++ == 0) ? sdf.format(t) : ("," + sdf.format(t)));
                } else {
                    sb.append((i++ == 0) ? t.toString() : ("," + t.toString()));
                }
            } else {
                try {
                    throw new Exception("Array.toString()方法仅支持Integer,Short,Long,Boolean,Byte,String,Character,Float,Double,Date");
                } catch (Exception e) {
                    e.printStackTrace();
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 比较两个 List 的值是否相等
     *
     * @param a
     * @param b
     * @Author: 杨国群
     * @Date: 2017/11/13 11:25
     * @version 1.0
     */
    public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
        if (a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

}
