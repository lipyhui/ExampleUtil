package com.android.lipy.utils.plug;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 作者：lipy
 * 日期：2019/6/25 16:02
 * <p>
 * 描述：resource 反射处理类
 */
public class ResourceUtils {

    /**
     * 获取第三方 app 的 Context
     *
     * @param context     当前 APP 的 Context
     * @param packageName
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static Context getRemoteContext(Context context, String packageName) throws PackageManager.NameNotFoundException {
        return context.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
    }

    /**
     * 通过名字获取 ID
     *
     * @param context 待获取资源的 Context
     * @param resType layout、id、color、string、drawable
     * @param resName
     * @return
     */
    public static int getId(Context context, String resType, String resName) {
        return context.getResources().getIdentifier(resName, resType, context.getPackageName());
    }

    /**
     * 获取布局文件的资源 ID
     *
     * @param context 待获取资源的 Context
     * @param name    layout 文件名
     * @return
     */
    public static int getLayoutId(Context context, String name) {
        return getId(context, "layout", name);
    }

    /**
     * 获取布局文件的 View
     *
     * @param context  待获取资源的 Context
     * @param layoutId
     * @return
     */
    public static View getLayout(Context context, int layoutId) {
        return ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutId, null);
    }

    /**
     * 从控件中获取资源的 ID
     *
     * @param context 待获取资源的 Context
     * @param name    id 名称
     * @return
     */
    public static int getViewId(Context context, String name) {
        return getId(context, "id", name);
    }

    /**
     * 获取颜色
     *
     * @param context
     * @param name
     * @return
     */
    public static int getColorId(Context context, String name) {
        return getId(context, "color", name);
    }

    /**
     * 从 strings.xml 里面获取资源的ID
     *
     * @param context 待获取资源的 Context
     * @param name
     * @return
     */
    public static int getStringId(Context context, String name) {
        return getId(context, "string", name);
    }

    /**
     * 从 Drawable 里面获取资源的ID
     *
     * @param context 待获取资源的 Context
     * @param name
     * @return
     */
    public static int getDrawableId(Context context, String name) {
        return getId(context, "drawable", name);
    }

    /**
     * 从 Mipmap 里面获取资源的ID
     *
     * @param context 待获取资源的 Context
     * @param name
     * @return
     */
    public static int getMipmapId(Context context, String name) {
        return getId(context, "mipmap", name);
    }

    /**
     * 获取颜色
     *
     * @param context
     * @param name
     * @return 颜色值
     */
    public static int getResColor(Context context, String name) {
        return context.getResources().getColor(getColorId(context, name));
    }

    /**
     * 从 strings.xml 里面获取字符串
     *
     * @param context 待获取资源的 Context
     * @param name
     * @return
     */
    public static String getResString(Context context, String name) {
        return context.getString(getStringId(context, name));
    }

    /**
     * 从Drawable目录获取 Drawable 对象
     *
     * @param context 待获取资源的 Context
     * @param name
     * @return
     */
    public static Drawable getResDrawable(Context context, String name) {
        return context.getResources().getDrawable(getDrawableId(context, name));
    }

    /**
     * 从Mipmap目录获取 Drawable 对象
     *
     * @param context 待获取资源的 Context
     * @param name
     * @return
     */
    public static Drawable getResMipmap(Context context, String name) {
        return context.getResources().getDrawable(getMipmapId(context, name));
    }
}
