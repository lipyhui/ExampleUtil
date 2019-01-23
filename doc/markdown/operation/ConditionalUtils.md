# ConditionalUtils
&emsp;&emsp;ConditionalUtils是一个逻辑处理工具。该工具的目的是提供Kotlin逻辑方法的扩展，优化代码可阅读性，包括一些与、或逻辑方法。

# 目录
* [allNotNull --> 判断所有条件不为空](#allNotNull)
* [anyNull --> 判断是否有条件为空](#anyNull)
* [allTrue --> 判断所有条件为真](#allTrue)
* [anyTrue --> 判断是否有条件为真](#anyTrue)
* [allFalse --> 判断所有条件为假](#allFalse)
* [anyFalse --> 判断是否有条件为假](#anyFalse)

## allNotNull
&emsp;&emsp;所有条件不为空则返回true，或者调用回调方法。
```
//所有条件不为空则返回true，否正为false
fun <T> allNotNull(vararg values: T?): Boolean

//Kotlin 扩展，所有条件不为空则回调 action
fun <T, R> allNotNull(vararg values: T?, action: (List<T>) -> R?): R?

//Java 扩展，所有条件不为空则回调 action
fun <T, R> allNotNull(action: (List<T>) -> R?, vararg values: T?): R?
```

## anyNull
&emsp;&emsp;有条件为空则返回true，或者调用回调方法。
```
//有条件为空则返回true，否正为false
fun <T> anyNull(vararg values: T?): Boolean

//Kotlin 扩展，有条件为空则回调 action
fun <T, R> anyNull(vararg values: T?, action: () -> R?): R?

//Java 扩展，有条件为空则回调 action
fun <T, R> anyNull(action: () -> R?, vararg values: T?): R?
```

## allTrue
&emsp;&emsp;所有条件为真则返回true，或者调用回调方法。
```
//所有条件为真则返回true，否正为false
fun allTrue(vararg values: Boolean?): Boolean

//Kotlin 扩展，所有条件为真则回调 action
fun <R> allTrue(vararg values: Boolean, action: () -> R?): R?

//Java 扩展，所有条件为真则回调 action
fun <R> allTrue(action: () -> R?, vararg values: Boolean): R? 
```

## anyTrue
&emsp;&emsp;有条件为真则返回true，或者调用回调方法。
```
//有条件为真则返回true，否正为false
fun anyTrue(vararg values: Boolean?): Boolean

//Kotlin 扩展，有条件为真则回调 action
fun <R> anyTrue(vararg values: Boolean, action: () -> R?): R?

//Java 扩展，有条件为真则回调 action
fun <R> anyTrue(action: () -> R?, vararg values: Boolean): R? 
```

## allFalse
&emsp;&emsp;所有条件为假则返回true，或者调用回调方法。
```
//所有条件为假则返回true，否正为false
fun allFalse(vararg values: Boolean?): Boolean

//Kotlin 扩展，所有条件为假则回调 action
fun <R> allFalse(vararg values: Boolean, action: () -> R?): R?

//Java 扩展，所有条件为假则回调 action
fun <R> allFalse(action: () -> R?, vararg values: Boolean): R? 
```

## anyFalse
&emsp;&emsp;有条件为假则返回true，或者调用回调方法。
```
//有条件为假则返回true，否正为false
fun anyFalse(vararg values: Boolean?): Boolean

//Kotlin 扩展，有条件为假则回调 action
fun <R> anyFalse(vararg values: Boolean, action: () -> R?): R?

//Java 扩展，有条件为假则回调 action
fun <R> anyFalse(action: () -> R?, vararg values: Boolean): R? 
```