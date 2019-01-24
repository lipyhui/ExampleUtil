# FileUtils
&emsp;&emsp;FileUtils是一个文件处理工具。该工具的目的是提供一些常用的文件操作方法。

# 目录
- [getFileByPath --> 文件路径转换为文件](#getFileByPath)
- [isExists --> 文件路径转换为文件](#isExists)
- [rename --> 修改文件或文件夹名](#rename)
- [isDir --> 判断是否是文件夹](#isDir)
- [isFile --> 判断是否是文件](#isFile)
- [createDir --> 创建文件夹](#createDir)
- [createFile --> 创建文件](#createFile)
- [copyDir --> 复制文件夹](#copyDir)
- [copyFile --> 负责文件](#copyFile)
- [moveDir --> 移动文件夹](#moveDir)
- [moveFile --> 移动文件](#moveFile)
- [delete --> 删除文件或文件夹](#delete)
- [deleteDir --> 删除文件夹](#deleteDir)
- [deleteFile --> 删除文件](#deleteFile)

## getFileByPath
&emsp;&emsp;把文件路径转换为文件，文件路径为空则返回空。
```
fun getFileByPath(filePath: String?): File?
```

## isExists
&emsp;&emsp;文件是否存在，存在则返回 true。
```
fun isExists(filePath: String?): Boolean
fun isExists(file: File?): Boolean
```

## rename
&emsp;&emsp;修改文件夹或者文件夹名称，修改成功则返回 true。</br>
&emsp;&emsp;**注：修改文件名需要带文件后缀**
```
fun isExists(filePath: String?): Boolean
fun isExists(file: File?): Boolean
```

## isDir
&emsp;&emsp;文件或文件路径是否为文件夹，是文件夹则返回 true。
```
fun isDir(dirPath: String?): Boolean
fun isDir(file: File?): Boolean
```

## isFile
&emsp;&emsp;文件或文件路径是否为文件，是文件则返回 true。
```
fun isFile(filePath: String?): Boolean
fun isFile(file: File?): Boolean
```

## createDir
&emsp;&emsp;创建文件夹，创建成功则返回 true。
```
fun createDir(dirPath: String?): Boolean
fun createDir(file: File?): Boolean
```

## createFile
&emsp;&emsp;创建文件，创建成功则返回 true。
```
fun createFile(filePath: String?): Boolean
fun createFile(file: File?, isForce: Boolean): Boolean
```

## copyDir
&emsp;&emsp;复制文件夹，复制成功则返回 true。
```
fun copyDir(srcDirPath: String?, destDirPath: String?): Boolean
//监听为空或者监听返回 true 则替换目标文件夹（目标文件夹存在则先删除目标文件夹，后复制），否则不替换目标文件夹
fun copyDir(srcDirPath: String?, destDirPath: String?, listener: OnReplaceListener?): Boolean

fun copyDir(srcDir: File?, destDir: File?): Boolean
//监听为空或者监听返回 true 则替换目标文件夹（目标文件夹存在则先删除目标文件夹，后复制），否则不替换目标文件夹
fun copyDir(srcDir: File?, destDir: File?, listener: OnReplaceListener?): Boolean
```

## copyFile
&emsp;&emsp;复制文件，复制成功则返回 true。
```
fun copyFile(srcFilePath: String?, destFilePath: String?): Boolean
//监听为空或者监听返回 true 则替换目标文件（目标文件存在则先删除目标文件，后复制），否则不替换目标文件
fun copyFile(srcFilePath: String?, destFilePath: String?, listener: OnReplaceListener?): Boolean

fun copyFile(srcFile: File?, destFile: File?): Boolean
//监听为空或者监听返回 true 则替换目标文件（目标文件存在则先删除目标文件，后复制），否则不替换目标文件
fun copyFile(srcFile: File?, destFile: File?, listener: OnReplaceListener?): Boolean
```

## moveDir
&emsp;&emsp;移动文件夹，移动成功则返回 true。
```
fun moveDir(srcDirPath: String?, destDirPath: String?): Boolean
//监听为空或者监听返回 true 则替换目标文件夹（目标文件夹存在则先删除目标文件夹，后移动），否则不替换目标文件夹
fun moveDir(srcDirPath: String?, destDirPath: String?, listener: OnReplaceListener?): Boolean

fun moveDir(srcDir: File?, destDir: File?): Boolean
//监听为空或者监听返回 true 则替换目标文件夹（目标文件夹存在则先删除目标文件夹，后移动），否则不替换目标文件夹
fun moveDir(srcDir: File?, destDir: File?, listener: OnReplaceListener?): Boolean
```

## moveFile
&emsp;&emsp;移动文件，移动成功则返回 true。
```
fun moveFile(srcFilePath: String?, destFilePath: String?): Boolean
//监听为空或者监听返回 true 则替换目标文件（目标文件存在则先删除目标文件，后复制），否则不替换目标文件
fun moveFile(srcFilePath: String?, destFilePath: String?, listener: OnReplaceListener?): Boolean

fun moveFile(srcFile: File?, destFile: File?): Boolean
//监听为空或者监听返回 true 则替换目标文件（目标文件存在则先删除目标文件，后复制），否则不替换目标文件
fun moveFile(srcFile: File?, destFile: File?, listener: OnReplaceListener?): Boolean
```

## delete
&emsp;&emsp;删除文件、文件夹，删除成功则返回 true。
```
fun delete(filePath: String?): Boolean
fun delete(file: File?): Boolean
```

## deleteDir
&emsp;&emsp;删除文件夹，删除成功则返回 true。
```
fun deleteDir(dirPath: String?): Boolean
fun deleteDir(dir: File?): Boolean
```

## deleteFile
&emsp;&emsp;删除文件，删除成功则返回 true。
```
fun deleteFile(dirPath: String?): Boolean
fun deleteFile(dir: File?): Boolean
```

## deleteFile
&emsp;&emsp;删除文件，删除成功则返回 true。
```
fun deleteFile(dirPath: String?): Boolean
fun deleteFile(dir: File?): Boolean
```