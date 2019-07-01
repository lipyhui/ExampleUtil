package com.example.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.lipy.elog.ELog;
import com.android.lipy.utils.constant.EnumConstant;
import com.android.lipy.utils.operation.ConditionalUtils;
import com.android.lipy.utils.plug.ResourceUtils;
import com.android.lipy.utils.safe.data.EncryptUtils;

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

    /**
     * 加密解密EncryptUtils演示代码
     */
    public static void encryptUtilsTest() {
        ELog.INSTANCE.e(">>>>>>>>>>>>>> MD5 %s", EncryptUtils.getInstance().encryptMD5ToString("sssssssssss"));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> Hmac MD5 %s", EncryptUtils.getInstance().encryptHmacMD5ToString("sssssssssss", "key1"));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> MD5 File %s", EncryptUtils.getInstance().encryptMD5File2String("/sdcard/MD5File.txt"));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> SHA 1 %s", EncryptUtils.getInstance().encryptSHAToString("sssssssssss", EnumConstant.SHA1));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> SHA 224 %s", EncryptUtils.getInstance().encryptSHAToString("sssssssssss", EnumConstant.SHA224));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> SHA 256 %s", EncryptUtils.getInstance().encryptSHAToString("sssssssssss", EnumConstant.SHA256));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> SHA 384 %s", EncryptUtils.getInstance().encryptSHAToString("sssssssssss", EnumConstant.SHA384));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> SHA 512 %s", EncryptUtils.getInstance().encryptSHAToString("sssssssssss", EnumConstant.SHA512));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> Hmac SHA 1 %s", EncryptUtils.getInstance().encryptHmacSHAToString("sssssssssss", "key1", EnumConstant.HMAC_SHA1));
//        ELog.INSTANCE.e(">>>>>>>>>>>>>> Hmac SHA 224 %s", EncryptUtils.getInstance().encryptHmacSHAToString("sssssssssss", "key1", EnumConstant.HMAC_SHA224));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> Hmac SHA 256 %s", EncryptUtils.getInstance().encryptHmacSHAToString("sssssssssss", "key1", EnumConstant.HMAC_SHA256));
//        ELog.INSTANCE.e(">>>>>>>>>>>>>> Hmac SHA 384 %s", EncryptUtils.getInstance().encryptHmacSHAToString("sssssssssss", "key1", EnumConstant.HMAC_SHA384));
        ELog.INSTANCE.e(">>>>>>>>>>>>>> Hmac SHA 512 %s", EncryptUtils.getInstance().encryptHmacSHAToString("sssssssssss", "key1", EnumConstant.HMAC_SHA512));
//        ELog.INSTANCE.e(">>>>>>>>>>>>>> DES %s", EncryptUtils.getInstance().encryptDES2String("sssssssssss".getBytes(),new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, "desede/CBC/PKCS5Padding", "".getBytes()));
    }

    public static void ResourceUtilsTest(Context context) {
        Context plugContext;
        try {
            plugContext = ResourceUtils.getRemoteContext(context, "plug.app.package");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return;
        }

        int layoutId = ResourceUtils.getLayoutId(plugContext, "activity_test");

        View view = ResourceUtils.getLayout(plugContext, layoutId);
        ImageView imageView = view.findViewById(ResourceUtils.getViewId(plugContext, "test_img"));
        imageView.setBackgroundColor(ResourceUtils.getResColor(plugContext, "screen_color_value09"));

        /** view 引入第三方 app 的 layout
        FrameLayout test = findViewById(R.id.test);
        test.addView(view);
         */
    }
}
