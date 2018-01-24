/**
 * 
 */
package com.quentin.example.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * List工具类
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
public class ListUtils {
	/**
	 * 判断List是否为空
	 * @Author: guoqun.yang
	 * @Date:   2018/1/18 14:18
	 * @param   list
	 * @version 1.0
	 */
	public static <T> boolean isEmpty(List<T> list) {
		if (list == null || list.size() == 0)
			return true;
		return false;
	}

	/**
	 * 判断List中是否含有某个对象
	 * @Author: guoqun.yang
	 * @Date:   2018/1/18 14:18
	 * @param   list
	 * @param   t
	 * @version 1.0
	 */
	public static <T> boolean has(List<T> list, T t) {
		if (list == null || list.size() == 0)
			return false;
		if (list.contains(t))
			return true;
		return false;
	}

	/**
	 * 根据Index获取List中的元素
	 * @Author: guoqun.yang
	 * @Date:   2018/1/18 14:18
	 * @param   list
	 * @param   index
	 * @version 1.0
	 */
	public static <T> T get(List<T> list, int index) {
		if (isEmpty(list) || index < 0 || index >= list.size())
			return null;
		return list.get(index);
	}

	/**
	 * 根据Index移除某个元素
	 * @Author: guoqun.yang
	 * @Date:   2018/1/18 14:19
	 * @param   list
	 * @param   index
	 * @version 1.0
	 */
	public static <T> List<T> remove(List<T> list, int index) {
		if (isEmpty(list))
			return null;
		if (index < 0 || index >= list.size())
			return list;
		list.remove(index);
		return list;
	}

	/**
	 * List转数组
	 * @Author: guoqun.yang
	 * @Date:   2018/1/18 14:19
	 * @param   list
	 * @param   array
	 * @version 1.0
	 */
	public static <T> T[] asArray(List<T> list, T[] array) {
		if (list == null || list.size() == 0)
			return null;
		return list.toArray(array);
	}

	/**
	 * 排序
	 * @Author: guoqun.yang
	 * @Date:   2018/1/18 14:19
	 * @param   list
	 * @param   c
	 * @version 1.0
	 */
	public static <T> List<T> sort(List<T> list, Comparator<T> c) {
		Collections.sort(list, c);
		return list;
	}

}
