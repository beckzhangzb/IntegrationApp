package com.job.cannal.datasync.util;

import java.io.Serializable;

/**
 * Created by haha on 2020/8/24.
 */
public enum Type implements Serializable {
    INSERT, DELETE, UPDATE;

    public static Type fromName(String type){
        for(Type t : values()){
            if(t.name().equals(type)){
                return t;
            }
        }
        return null;
    }
}
