package com.quentin.example.config.dynamicsdb;

/**
 * 定义数据源持有者，切换数据源使用
 * @Auth Created by guoqun.yang
 * @Date Created in 13:23 2017/12/18
 * @Version 1.0
 */
public final class DataSourceHolder {
    private static ThreadLocal<DataSourceEnum> threadLocal = new ThreadLocal();

    private static final DataSourceEnum DEFAULTDBTYPE = DataSourceEnum.lbdb;

    public static void getDefaultDS(){
        setDS(DEFAULTDBTYPE);
    }

    public static void setDS(DataSourceEnum key){
        threadLocal.set(key);
    }

    public static DataSourceEnum getCurrentDS(){
        return threadLocal.get();
    }

    public static void removeDS(){
        threadLocal.remove();
    }

}
