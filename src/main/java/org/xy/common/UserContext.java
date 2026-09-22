package org.xy.common;

public class UserContext {
    public static final ThreadLocal<Long> threadLocal=new ThreadLocal<Long>();

    public static void setUserId(Long userId){
        threadLocal.set(userId);
    }
    public static Long getUserId(){
        return threadLocal.get();
    }
    //用完后要清理数据
    public static void remove(){
        threadLocal.remove();
    }

}
