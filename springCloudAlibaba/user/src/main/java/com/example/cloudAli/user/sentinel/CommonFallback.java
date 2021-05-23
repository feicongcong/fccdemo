package com.example.cloudAli.user.sentinel;


import com.example.cloudAli.commonAli.utils.R;

/**
 * @author Fox
 */
public class CommonFallback {
    /**
     * 注意： 必须为 static 函数
     * @param e
     * @return
     */
    public static R fallback(Integer id, Throwable e){
        return R.error(-2,"===被异常降级啦==="+e.getMessage());
    }
}
