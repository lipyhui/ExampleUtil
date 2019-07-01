# ResourceUtil
&emsp;&emsp;ResourceUtil是一个使用第三方 APP、APP插件的工具类，可用于间接使用第三方APP lib、换肤等。由于是从第三方获取资源，记得捕获异常。

# 目录
- [getRemoteContext --> 获取插件 Context](#getRemoteContext)
- [getId --> 通过名字获取ID](#getId)
- [getLayoutId --> 获取布局文件ID](#getLayoutId)
- [getLayout --> 获取布局文件RootView](#getLayout)
- [getViewId --> 获取布局文件中View的ID](#getViewId)
- [getColorId --> 获取颜色资源ID](#getColorId)
- [getStringId --> 获取字符串资源ID](#getStringId)
- [getDrawableId --> 获取Drawable资源ID](#getDrawableId)
- [getMipmapId --> 获取Mipmap资源ID](#getMipmapId)
- [getResColor --> 获取资源颜色值](#getResColor)
- [getResString --> 获取资源字符串](#getResString)
- [getResDrawable --> 获取资源Drawable](#getResDrawable)
- [getResMipmap --> 获取资源Drawable](#getResMipmap)

## getRemoteContext
&emsp;&emsp;获取第三方 APP、APP插件的 Context
```
public static Context getRemoteContext(Context context, String packageName) throws PackageManager.NameNotFoundException
```

## getId
&emsp;&emsp;获取第三方资源 ID，资源类型由参数 resType （包括：layout、id、color、string、drawable）决定。
```
public static int getId(Context context, String resType, String resName)
```

## getLayoutId
&emsp;&emsp;获取第三方 layout ID。
```
public static int getLayoutId(Context context, String name)
```

## getLayout
&emsp;&emsp;获取第三方 layout rootView。
```
public static View getLayout(Context context, int layoutId)
```

## getViewId
&emsp;&emsp;获取第三方 layout 中的 view ID
```
public static int getViewId(Context context, String name)
```

## getColorId
&emsp;&emsp;获取第三方 color 资源 ID
```
public static int getColorId(Context context, String name)
```

## getStringId
&emsp;&emsp;获取第三方 string 资源 ID
```
public static int getStringId(Context context, String name)
```

## getDrawableId
&emsp;&emsp;获取第三方 drawable 资源 ID
```
public static int getDrawableId(Context context, String name)
```

## getMipmapId
&emsp;&emsp;获取第三方 mipmap 资源 ID
```
public static int getMipmapId(Context context, String name)
```

## getResColor
&emsp;&emsp;获取第三方 color 资源颜色值
```
public static int getResColor(Context context, String name)
```

## getResString
&emsp;&emsp;获取第三方 string 资源字符串
```
public static String getResString(Context context, String name)
```

## getResDrawable
&emsp;&emsp;获取第三方 drawable 资源 Drawable 对象
```
public static Drawable getResDrawable(Context context, String name)
```

## getResMipmap
&emsp;&emsp;获取第三方 mipmap 资源 Drawable 对象
```
public static Drawable getResMipmap(Context context, String name)
```