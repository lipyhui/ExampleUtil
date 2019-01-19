package com.example.util;

import com.android.lipy.elog.ELog;
import com.android.lipy.utils.operation.ConditionalUtils;

public class JavaTest {
    public static void conditionalUtilTest() {
        String javaTestStr = ConditionalUtils.INSTANCE.allNotNull(values -> {
            ELog.INSTANCE.e("############### java v1 %s, v2 %s", values[0], values[1]);
            return "this is a java test !!!!";
        }, "ssss", "ffffff");

        ELog.INSTANCE.e("############### JAVA ifAllNotNull %s", javaTestStr);
    }
}
