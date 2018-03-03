package com.github.wxiaoqi.blog.admin.util;

import java.math.BigDecimal;

/**
 * Created by linsheng on 18/3/2.
 */
public class NumberFormat {


    public static double toFied2(double number){
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
