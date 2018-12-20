package com.ali.work.singleton;

/**
 * @author zhangzb
 */
public class MySingleton {
    private static volatile MySingleton mySingleton = null;

    private MySingleton(){}

    /**
     * 获取单例
     * @return
     */
    public static MySingleton getInstance(){
        if (mySingleton == null) {
            synchronized (MySingleton.class) {
                if (mySingleton == null) {
                    mySingleton = new MySingleton();
                }
            }
        }
        return mySingleton;
    }

}
