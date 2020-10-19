package com.xbcxs.common.design.singleton;

/**
 * Singleton
 *
 * @author x
 * @date 2020/9/14
 */
public class Singleton {

    private static volatile Singleton singleton = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
