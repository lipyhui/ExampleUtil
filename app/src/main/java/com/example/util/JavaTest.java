package com.example.util;

import com.android.lipy.elog.ELog;
import com.android.lipy.utils.operation.ConditionalUtils;

public class JavaTest {
    /**
     * 逻辑运算ConditionalUtils演示代码
     */
    public static void conditionalUtilTest() {
        String javaTestStr = ConditionalUtils.INSTANCE.allNotNull(values -> {
            ELog.INSTANCE.e("############### java v1 %s, v2 %s", values.get(0), values.get(1));
            return "this is a java test !!!!";
        }, "ssss", "ffffff");

        ELog.INSTANCE.e("############### JAVA ifAllNotNull %s", javaTestStr);
    }
}
