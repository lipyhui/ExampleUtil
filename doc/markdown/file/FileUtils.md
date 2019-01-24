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
- [deleteAllInDir --> 删除文件夹下所有文件、文件夹](#deleteAllInDir)
- [deleteFilesInDir --> 删除文件夹下所有文件](#deleteFilesInDir)
- [deleteFilesInDirWithFilter --> 根据过滤条件删除文件夹下文件、文件夹](#deleteFilesInDirWithFilter)
- [listFilesInDir --> 获取文件夹下文件](#listFilesInDir)
- [listFilesInDirWithFilter --> 根据过滤条件获取文件夹下文件](#listFilesInDirWithFilter)
- [getFileLastModified --> 获取文件修改时间](#getFileLastModified)
- [getFileCharsetSimple --> 获取文件编码格式](#getFileCharsetSimple)
- [getFileLines --> 获取文件行数](#getFileLines)
- [getDirSize、getDirLength --> 获取文件夹大小](#getDirSizegetDirLength)
- [getFileSize、getFileLength --> 获取文件大小](#getFileSizegetFileLength)
- [getFileMD5ToString、getFileMD5 --> 获取文件MD5值](#getFileMD5ToStringgetFileMD5)
- [getDirPath --> 获取文件夹路径](#getDirPath)
- [getFileName、getFileNameNoExtension --> 获取文件名](#getFileNamegetFileNameNoExtension)
- [getFileExtension --> 获取文件后缀](#getFileExtension)

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
fun rename(filePath: String?, newName: String): Boolean
fun rename(file: File?, newName: String): Boolean
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

## deleteAllInDir
&emsp;&emsp;删除文件夹下所有文件、文件夹，删除成功则返回 true。
```
fun deleteAllInDir(dirPath: String?): Boolean
fun deleteAllInDir(dir: File?): Boolean
```

## deleteFilesInDir
&emsp;&emsp;删除文件夹下所有文件，删除成功则返回 true。
```
fun deleteFilesInDir(dirPath: String?): Boolean
fun deleteFilesInDir(dir: File?): Boolean
```

## deleteFilesInDirWithFilter
&emsp;&emsp;根据过滤条件删除文件夹下文件、文件夹，删除成功则返回 true。
```
fun deleteFilesInDirWithFilter(dirPath: String?, filter: FileFilter): Boolean
fun deleteFilesInDirWithFilter(dir: File?, filter: FileFilter): Boolean
```

## listFilesInDir
&emsp;&emsp;获取文件夹下文件，获取成功则返回文件列表。
```
//不获取子目录下的文件
fun listFilesInDir(dirPath: String?): List<File>?
fun listFilesInDir(dir: File): List<File>?

//isRecursive 为 true 则获取子目录下文件
fun listFilesInDir(dirPath: String?, isRecursive: Boolean): List<File>?
fun listFilesInDir(dir: File?, isRecursive: Boolean): List<File>?
```

## listFilesInDirWithFilter
&emsp;&emsp;根据过滤条件获取文件夹下文件，获取成功则返回文件列表。
```
//不获取子目录下的文件
fun listFilesInDirWithFilter(dirPath: String?, filter: FileFilter): List<File>?
fun listFilesInDirWithFilter(dir: File?, filter: FileFilter): List<File>?

//isRecursive 为 true 则获取子目录下文件
fun listFilesInDirWithFilter(dirPath: String?, filter: FileFilter, isRecursive: Boolean): List<File>?
fun listFilesInDirWithFilter(dir: File?, filter: FileFilter, isRecursive: Boolean): List<File>?
```

## getFileLastModified
&emsp;&emsp;获取文件修改时间，获取失败则返回-1。
```
fun getFileLastModified(filePath: String): Long
fun getFileLastModified(file: File?): Long
```

## getFileCharsetSimple
&emsp;&emsp;获取文件修改时间，获取成功则返回编码格式。
```
fun getFileCharsetSimple(filePath: String?): String?
fun getFileCharsetSimple(file: File?): String?
```

## getFileLines
&emsp;&emsp;获取文件行数，获取失败则返回-1。
```
fun getFileLines(filePath: String?): Int
fun getFileLines(file: File?): Int
```

## getDirSize、getDirLength
&emsp;&emsp;获取文件夹大小。
```
//返回可读性强的字符串，获取失败返回空字符串
fun getDirSize(dirPath: String?): String
fun getDirSize(dir: File?): String

//返回文件夹大小，获取失败返回 -1
fun getDirLength(dirPath: String): Long
fun getDirLength(dir: File?): Long
```

## getFileSize、getFileLength
&emsp;&emsp;获取文件大小。
```
//返回可读性强的字符串，获取失败返回空字符串
fun getFileSize(filePath: String?): String
fun getFileSize(file: File?): String

//返回文件大小，获取失败返回 -1
fun getFileLength(filePath: String?): Long
fun getFileLength(file: File?): Long
```

## getFileMD5ToString、getFileMD5
&emsp;&emsp;获取文件大小。
```
//返回可读性强的字符串，获取失败返回空字符串
fun getFileMD5ToString(filePath: String?): String
fun getFileMD5ToString(file: File?): String

//返回文件大小，获取失败返回 null
fun getFileMD5(filePath: String?): ByteArray?
fun getFileMD5(file: File?): ByteArray?
```

## getDirPath
&emsp;&emsp;获取文件夹路径，获取失败则返回空字符串。
```
fun getDirPath(file: File?): String
fun getDirPath(filePath: String?): String
```

## getFileName、getFileNameNoExtension
&emsp;&emsp;获取文件名，获取失败则返回空字符串。
```
//带文件后缀
fun getFileName(file: File?): String
fun getFileName(filePath: String?): String

//不带文件后缀
fun getFileNameNoExtension(file: File?): String
fun getFileNameNoExtension(filePath: String?): String
```

## getFileExtension
&emsp;&emsp;获取文件后缀，获取失败则返回空字符串。
```
fun getFileExtension(file: File?): String
fun getFileExtension(filePath: String?): String
```