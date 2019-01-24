package com.android.lipy.utils.file

import com.android.lipy.utils.operation.ConditionalUtils
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.security.DigestInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.experimental.and

@Suppress("UNUSED", "MemberVisibilityCanBePrivate")
object FileUtils {
    private const val TAG = "Utils_FileUtil"
    private val LINE_SEP = System.getProperty("line.separator")

    interface OnReplaceListener {
        fun onReplace(): Boolean
    }

    /**
     * Return the file by path.
     *
     * @param filePath The path of file.
     * @return the file
     */
    fun getFileByPath(filePath: String?): File? {
        return if (filePath.isNullOrEmpty()) null else File(filePath)
    }

    /**
     * Return whether the file exists.
     *
     * @param filePath The path of file.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isExists(filePath: String?): Boolean {
        return isExists(getFileByPath(filePath))
    }

    /**
     * Return whether the file exists.
     *
     * @param file The file.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isExists(file: File?): Boolean {
        return file != null && file.exists()
    }

    /**
     * Rename the file.
     *
     * @param filePath The path of file.
     * @param newName  The new name of file. eg: newName.txt
     * @return `true`: success<br></br>`false`: fail
     */
    fun rename(filePath: String?, newName: String): Boolean {
        return rename(getFileByPath(filePath), newName)
    }

    /**
     * Rename the file.
     *
     * @param file    The file.
     * @param newName The new name of file. eg: newName.txt
     * @return `true`: success<br></br>`false`: fail
     */
    fun rename(file: File?, newName: String): Boolean {
        file?.let {
            if (!file.exists() || isSpace(newName)) {
                return false
            }

            if (newName == file.name) {
                return true
            }

            val newFile = File(file.parent + File.separator + newName)
            return !newFile.exists() && file.renameTo(newFile)
        }

        return false
    }

    /**
     * Return whether it is a directory.
     *
     * @param dirPath The path of directory.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isDir(dirPath: String?): Boolean {
        return isDir(getFileByPath(dirPath))
    }

    /**
     * Return whether it is a directory.
     *
     * @param file The file.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isDir(file: File?): Boolean {
        return file != null && file.exists() && file.isDirectory
    }

    /**
     * Return whether it is a file.
     *
     * @param filePath The path of file.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFile(filePath: String?): Boolean {
        return isFile(getFileByPath(filePath))
    }

    /**
     * Return whether it is a file.
     *
     * @param file The file.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFile(file: File?): Boolean {
        return file != null && file.exists() && file.isFile
    }

    /**
     * Create a directory if it doesn't exist, otherwise do nothing.
     *
     * @param dirPath The path of directory.
     * @return `true`: exists or creates successfully<br></br>`false`: otherwise
     */
    fun createDir(dirPath: String?): Boolean {
        return createDir(getFileByPath(dirPath))
    }

    /**
     * Create a directory if it doesn't exist, otherwise do nothing.
     *
     * @param file The file.
     * @return `true`: exists or creates successfully<br></br>`false`: otherwise
     */
    fun createDir(file: File?): Boolean {
        return file != null && if (file.exists()) file.isDirectory else file.mkdirs()
    }

    /**
     * Create a file if it doesn't exist, otherwise do nothing.
     *
     * @param filePath The path of file.
     * @return `true`: exists or creates successfully<br></br>`false`: otherwise
     */
    fun createFile(filePath: String?): Boolean {
        return createFile(getFileByPath(filePath), false)
    }

    /**
     * Create a file.
     * Create a file if it doesn't exist;
     * or delete it and create a file if isForce is True and the file exists,
     * otherwise do nothing.
     *
     * @param file The file.
     * @param isForce Whether to Force New File
     * @return `true`: exists or creates successfully<br></br>`false`: otherwise
     */
    fun createFile(file: File?, isForce: Boolean): Boolean {
        file?.let {
            if (isForce && file.exists() && !file.delete()) {
                return false
            } else if (!isForce && file.exists()) {
                return file.isFile
            }

            if (!createDir(file.parentFile)) {
                return false
            }

            return try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }

        return false

    }

    /**
     * Copy the directory.
     *
     * @param srcDirPath  The path of source directory.
     * @param destDirPath The path of destination directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyDir(srcDirPath: String?, destDirPath: String?): Boolean {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath))
    }

    /**
     * Copy the directory.
     *
     * @param srcDirPath  The path of source directory.
     * @param destDirPath The path of destination directory.
     * @param listener    The replace listener.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyDir(srcDirPath: String?, destDirPath: String?, listener: OnReplaceListener?): Boolean {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener)
    }

    /**
     * Copy the directory.
     *
     * @param srcDir  The source directory.
     * @param destDir The destination directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyDir(srcDir: File?, destDir: File?): Boolean {
        return copyOrMoveDir(srcDir, destDir, false)
    }

    /**
     * Copy the directory.
     *
     * @param srcDir   The source directory.
     * @param destDir  The destination directory.
     * @param listener The replace listener.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyDir(srcDir: File?, destDir: File?, listener: OnReplaceListener?): Boolean {
        return copyOrMoveDir(srcDir, destDir, false, listener)
    }

    /**
     * Copy the file.
     *
     * @param srcFilePath  The path of source file.
     * @param destFilePath The path of destination file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyFile(srcFilePath: String?, destFilePath: String?): Boolean {
        return copyFile(getFileByPath(srcFilePath), getFileByPath(destFilePath))
    }

    /**
     * Copy the file.
     *
     * @param srcFilePath  The path of source file.
     * @param destFilePath The path of destination file.
     * @param listener     The replace listener.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyFile(srcFilePath: String?, destFilePath: String?, listener: OnReplaceListener?): Boolean {
        return copyFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), listener)
    }

    /**
     * Copy the file.
     *
     * @param srcFile  The source file.
     * @param destFile The destination file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyFile(srcFile: File?, destFile: File?): Boolean {
        return copyOrMoveFile(srcFile, destFile, false)
    }

    /**
     * Copy the file.
     *
     * @param srcFile  The source file.
     * @param destFile The destination file.
     * @param listener The replace listener.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyFile(srcFile: File?, destFile: File?, listener: OnReplaceListener?): Boolean {
        return copyOrMoveFile(srcFile, destFile, listener, false)
    }

    /**
     * Move the directory.
     *
     * @param srcDirPath  The path of source directory.
     * @param destDirPath The path of destination directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun moveDir(srcDirPath: String?, destDirPath: String?): Boolean {
        return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath))
    }

    /**
     * Move the directory.
     *
     * @param srcDirPath  The path of source directory.
     * @param destDirPath The path of destination directory.
     * @param listener    The replace listener.
     * @return `true`: success<br></br>`false`: fail
     */
    fun moveDir(srcDirPath: String?, destDirPath: String?, listener: OnReplaceListener?): Boolean {
        return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener)
    }

    /**
     * Move the directory.
     *
     * @param srcDir  The source directory.
     * @param destDir The destination directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun moveDir(srcDir: File?, destDir: File?): Boolean {
        return copyOrMoveDir(srcDir, destDir, true)
    }

    /**
     * Move the directory.
     *
     * @param srcDir   The source directory.
     * @param destDir  The destination directory.
     * @param listener The replace listener.
     * @return `true`: success<br></br>`false`: fail
     */
    fun moveDir(srcDir: File?, destDir: File?, listener: OnReplaceListener?): Boolean {
        return copyOrMoveDir(srcDir, destDir, true, listener)
    }

    /**
     * Move the file.
     *
     * @param srcFilePath  The path of source file.
     * @param destFilePath The path of destination file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun moveFile(srcFilePath: String?, destFilePath: String?): Boolean {
        return moveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath))
    }

    /**
     * Move the file.
     *
     * @param srcFilePath  The path of source file.
     * @param destFilePath The path of destination file.
     * @param listener     The replace listener.
     * @return `true`: success<br></br>`false`: fail
     */
    fun moveFile(srcFilePath: String?, destFilePath: String?, listener: OnReplaceListener?): Boolean {
        return moveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), listener)
    }

    /**
     * Move the file.
     *
     * @param srcFile  The source file.
     * @param destFile The destination file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun moveFile(srcFile: File?, destFile: File?): Boolean {
        return copyOrMoveFile(srcFile, destFile, true)
    }

    /**
     * Move the file.
     *
     * @param srcFile  The source file.
     * @param destFile The destination file.
     * @param listener The replace listener.
     * @return `true`: success<br></br>`false`: fail
     */
    fun moveFile(srcFile: File?, destFile: File?, listener: OnReplaceListener?): Boolean {
        return copyOrMoveFile(srcFile, destFile, listener, true)
    }

    /**
     * Delete the directory.
     *
     * @param filePath The path of file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun delete(filePath: String?): Boolean {
        return delete(getFileByPath(filePath))
    }

    /**
     * Delete the directory.
     *
     * @param file The file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun delete(file: File?): Boolean {
        file?.let {
            return if (file.isDirectory) deleteDir(file) else deleteFile(file)
        }

        return false
    }

    /**
     * Delete the directory.
     *
     * @param dirPath The path of directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteDir(dirPath: String?): Boolean {
        return deleteDir(getFileByPath(dirPath))
    }

    /**
     * Delete the directory.
     *
     * @param dir The directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteDir(dir: File?): Boolean {
        dir?.takeIf { dir.exists() }
                ?.takeIf { dir.isDirectory }
                ?.let { dir.listFiles() }
                ?.takeIf { it.size >= 0 }
                ?.forEach {
                    if (it.isFile) {
                        if (!it.delete())
                            return false
                    } else if (it.isDirectory) {
                        if (!deleteDir(it))
                            return false
                    }
                }
                ?.let { return dir.delete() }

        return false
    }

    /**
     * Delete the file.
     *
     * @param srcFilePath The path of source file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteFile(srcFilePath: String?): Boolean {
        return deleteFile(getFileByPath(srcFilePath))
    }

    /**
     * Delete the file.
     *
     * @param file The file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteFile(file: File?): Boolean {
        return file != null && (!file.exists() || file.isFile && file.delete())
    }

    /**
     * Delete the all in directory.
     *
     * @param dirPath The path of directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteAllInDir(dirPath: String?): Boolean {
        return deleteAllInDir(getFileByPath(dirPath))
    }

    /**
     * Delete the all in directory.
     *
     * @param dir The directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteAllInDir(dir: File?): Boolean {
        return deleteFilesInDirWithFilter(dir, FileFilter { true })
    }

    /**
     * Delete all files in directory.
     *
     * @param dirPath The path of directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteFilesInDir(dirPath: String?): Boolean {
        return deleteFilesInDir(getFileByPath(dirPath))
    }

    /**
     * Delete all files in directory.
     *
     * @param dir The directory.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteFilesInDir(dir: File?): Boolean {
        return deleteFilesInDirWithFilter(dir, FileFilter { pathname -> pathname.isFile })
    }

    /**
     * Delete all files that satisfy the filter in directory.
     *
     * @param dirPath The path of directory.
     * @param filter  The filter.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteFilesInDirWithFilter(dirPath: String?, filter: FileFilter): Boolean {
        return deleteFilesInDirWithFilter(getFileByPath(dirPath), filter)
    }

    /**
     * Delete all files that satisfy the filter in directory.
     *
     * @param dir    The directory.
     * @param filter The filter.
     * @return `true`: success<br></br>`false`: fail
     */
    fun deleteFilesInDirWithFilter(dir: File?, filter: FileFilter): Boolean {
        dir?.takeIf { dir.isDirectory }
                ?.let {
                    if (!dir.exists()) {
                        return true
                    }

                    dir
                }
                ?.let { dir.listFiles() }
                ?.takeIf { it.isNotEmpty() }
                ?.forEach {
                    if (filter.accept(it)) {
                        if (it.isFile) {
                            if (!it.delete()) return false
                        } else if (it.isDirectory) {
                            if (!deleteDir(it)) return false
                        }
                    }
                }
                ?.let {
                    return true
                }

        return false
    }

    /**
     * Return the files in directory.
     *
     * Doesn't traverse subdirectories
     *
     * @param dirPath The path of directory.
     * @return the files in directory
     */
    fun listFilesInDir(dirPath: String?): List<File>? {
        return listFilesInDir(dirPath, false)
    }

    /**
     * Return the files in directory.
     *
     * Doesn't traverse subdirectories
     *
     * @param dir The directory.
     * @return the files in directory
     */
    fun listFilesInDir(dir: File): List<File>? {
        return listFilesInDir(dir, false)
    }

    /**
     * Return the files in directory.
     *
     * @param dirPath     The path of directory.
     * @param isRecursive True to traverse subdirectories, false otherwise.
     * @return the files in directory
     */
    fun listFilesInDir(dirPath: String?, isRecursive: Boolean): List<File>? {
        return listFilesInDir(getFileByPath(dirPath), isRecursive)
    }

    /**
     * Return the files in directory.
     *
     * @param dir         The directory.
     * @param isRecursive True to traverse subdirectories, false otherwise.
     * @return the files in directory
     */
    fun listFilesInDir(dir: File?, isRecursive: Boolean): List<File>? {
        return listFilesInDirWithFilter(dir, FileFilter { true }, isRecursive)
    }

    /**
     * Return the files that satisfy the filter in directory.
     *
     * Doesn't traverse subdirectories
     *
     * @param dirPath The path of directory.
     * @param filter  The filter.
     * @return the files that satisfy the filter in directory
     */
    fun listFilesInDirWithFilter(dirPath: String?, filter: FileFilter): List<File>? {
        return listFilesInDirWithFilter(getFileByPath(dirPath), filter, false)
    }

    /**
     * Return the files that satisfy the filter in directory.
     *
     * Doesn't traverse subdirectories
     *
     * @param dir    The directory.
     * @param filter The filter.
     * @return the files that satisfy the filter in directory
     */
    fun listFilesInDirWithFilter(dir: File?, filter: FileFilter): List<File>? {
        return listFilesInDirWithFilter(dir, filter, false)
    }

    /**
     * Return the files that satisfy the filter in directory.
     *
     * @param dirPath     The path of directory.
     * @param filter      The filter.
     * @param isRecursive True to traverse subdirectories, false otherwise.
     * @return the files that satisfy the filter in directory
     */
    fun listFilesInDirWithFilter(dirPath: String?, filter: FileFilter, isRecursive: Boolean): List<File>? {
        return listFilesInDirWithFilter(getFileByPath(dirPath), filter, isRecursive)
    }

    /**
     * Return the files that satisfy the filter in directory.
     *
     * @param dir         The directory.
     * @param filter      The filter.
     * @param isRecursive True to traverse subdirectories, false otherwise.
     * @return the files that satisfy the filter in directory
     */
    fun listFilesInDirWithFilter(dir: File?, filter: FileFilter, isRecursive: Boolean): List<File>? {
        val list = ArrayList<File>()

        return dir?.takeIf { isDir(dir) }
                ?.let { dir.listFiles() }
                ?.takeIf { it.isNotEmpty() }
                ?.forEach {
                    if (filter.accept(it)) {
                        list.add(it)
                    }
                    if (isRecursive && it.isDirectory) {
                        list.addAll(listFilesInDirWithFilter(it, filter, true)!!)
                    }
                }
                ?.let { return@let list }
    }

    /**
     * Return the time that the file was last modified.
     *
     * @param filePath The path of file.
     * @return the time that the file was last modified
     */

    fun getFileLastModified(filePath: String): Long {
        return getFileLastModified(getFileByPath(filePath))
    }

    /**
     * Return the time that the file was last modified.
     *
     * @param file The file.
     * @return the time that the file was last modified
     */
    fun getFileLastModified(file: File?): Long {
        return file?.lastModified() ?: -1
    }

    /**
     * Return the charset of file simply.
     *
     * @param filePath The path of file.
     * @return the charset of file simply
     */
    fun getFileCharsetSimple(filePath: String?): String? {
        return getFileCharsetSimple(getFileByPath(filePath))
    }

    /**
     * Return the charset of file simply.
     *
     * @param file The file.
     * @return the charset of file simply
     */
    fun getFileCharsetSimple(file: File?): String? {
        if (file == null) return null

        var p = 0
        var inputStream: InputStream? = null
        try {
            inputStream = BufferedInputStream(FileInputStream(file))
            p = (inputStream.read() shl 8) + inputStream.read()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        return when (p) {
            0xefbb -> "UTF-8"
            0xfffe -> "Unicode"
            0xfeff -> "UTF-16BE"
            else -> "GBK"
        }
    }

    /**
     * Return the number of lines of file.
     *
     * @param filePath The path of file.
     * @return the number of lines of file
     */
    fun getFileLines(filePath: String?): Int {
        return getFileLines(getFileByPath(filePath))
    }

    /**
     * Return the number of lines of file.
     *
     * @param file The file.
     * @return the number of lines of file
     */
    fun getFileLines(file: File?): Int {
        if (file == null) return -1

        var count = 1
        var inputStream: InputStream? = null
        try {
            inputStream = BufferedInputStream(FileInputStream(file))
            val buffer = ByteArray(1024)
            var readChars: Int
            if ("\n".endsWith(LINE_SEP)) {
                readChars = inputStream.read(buffer, 0, 1024)
                while (readChars != -1) {
                    for (i in 0 until readChars) {
                        if (buffer[i] == '\n'.toByte()) ++count
                    }

                    readChars = inputStream.read(buffer, 0, 1024)
                }
            } else {
                readChars = inputStream.read(buffer, 0, 1024)
                while (readChars != -1) {
                    for (i in 0 until readChars) {
                        if (buffer[i] == '\r'.toByte()) ++count
                    }

                    readChars = inputStream.read(buffer, 0, 1024)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return count
    }

    /**
     * Return the size of directory.
     *
     * @param dirPath The path of directory.
     * @return the size of directory
     */
    fun getDirSize(dirPath: String?): String {
        return getDirSize(getFileByPath(dirPath))
    }

    /**
     * Return the size of directory.
     *
     * @param dir The directory.
     * @return the size of directory
     */
    fun getDirSize(dir: File?): String {
        val len = getDirLength(dir)
        return if (len == -1L) "" else byte2FitMemorySize(len)
    }

    /**
     * Return the length of file.
     *
     * @param filePath The path of file.
     * @return the length of file
     */
    fun getFileSize(filePath: String?): String {
        val len = getFileLength(filePath)
        return if (len == -1L) "" else byte2FitMemorySize(len)
    }

    /**
     * Return the length of file.
     *
     * @param file The file.
     * @return the length of file
     */
    fun getFileSize(file: File?): String {
        val len = getFileLength(file)
        return if (len == -1L) "" else byte2FitMemorySize(len)
    }

    /**
     * Return the length of directory.
     *
     * @param dirPath The path of directory.
     * @return the length of directory
     */
    fun getDirLength(dirPath: String): Long {
        return getDirLength(getFileByPath(dirPath))
    }

    /**
     * Return the length of directory.
     *
     * @param dir The directory.
     * @return the length of directory
     */
    fun getDirLength(dir: File?): Long {
        if (!isDir(dir)) return -1

        var len: Long = 0
        dir?.listFiles()
                ?.takeIf { it.isNotEmpty() }
                ?.forEach { len += if (it.isDirectory) getDirLength(it) else it.length() }

        return len
    }

    /**
     * Return the length of file.
     *
     * @param filePath The path of file.
     * @return the length of file
     */
    fun getFileLength(filePath: String?): Long {

        filePath?.let { filePath.matches("[a-zA-z]+://[^\\s]*".toRegex()) }     //isURL
                ?.let {
                    if (!it) return getFileLength(getFileByPath(filePath))

                    try {
                        val conn = URL(filePath).openConnection() as HttpURLConnection
                        conn.setRequestProperty("Accept-Encoding", "identity")
                        conn.connect()
                        return if (conn.responseCode == 200) {
                            conn.contentLength.toLong()
                        } else -1
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    return getFileLength(getFileByPath(filePath))
                }

        return -1
    }

    /**
     * Return the length of file.
     *
     * @param file The file.
     * @return the length of file
     */
    fun getFileLength(file: File?): Long {
        return if (!isFile(file)) -1 else file!!.length()
    }

    /**
     * Return the MD5 of file.
     *
     * @param filePath The path of file.
     * @return the md5 of file
     */
    fun getFileMD5ToString(filePath: String?): String {
        val file = if (isSpace(filePath)) null else File(filePath)
        return getFileMD5ToString(file)
    }

    /**
     * Return the MD5 of file.
     *
     * @param file The file.
     * @return the md5 of file
     */
    fun getFileMD5ToString(file: File?): String {
        return bytes2HexString(getFileMD5(file))
    }

    /**
     * Return the MD5 of file.
     *
     * @param filePath The path of file.
     * @return the md5 of file
     */
    fun getFileMD5(filePath: String?): ByteArray? {
        return getFileMD5(getFileByPath(filePath))
    }

    /**
     * Return the MD5 of file.
     *
     * @param file The file.
     * @return the md5 of file
     */
    fun getFileMD5(file: File?): ByteArray? {
        file?.let {
            var dis: DigestInputStream? = null
            try {
                var md = MessageDigest.getInstance("MD5")
                val fis = FileInputStream(file)
                dis = DigestInputStream(fis, md)
                val buffer = ByteArray(1024 * 256)
                while (true) {
                    if (dis.read(buffer) <= 0) break
                }
                md = dis.messageDigest
                return md.digest()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    dis?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }

        return null
    }

    /**
     * Return the file's path of directory.
     *
     * @param file The file.
     * @return the file's path of directory
     */
    fun getDirName(file: File?): String {
        return if (file == null) "" else getDirName(file.absolutePath)
    }

    /**
     * Return the file's path of directory.
     *
     * @param filePath The path of file.
     * @return the file's path of directory
     */
    fun getDirName(filePath: String?): String {
        filePath?.takeUnless { isSpace(filePath) }
                ?.let {
                    if (isDir(filePath)) return filePath

                    val lastSep = filePath.lastIndexOf(File.separator)
                    return if (lastSep == -1) "" else filePath.substring(0, lastSep + 1)
                }

        return ""
    }

    /**
     * Return the name of file.
     *
     * @param file The file.
     * @return the name of file
     */
    fun getFileName(file: File?): String {
        return if (file == null) "" else getFileName(file.absolutePath)
    }

    /**
     * Return the name of file.
     *
     * @param filePath The path of file.
     * @return the name of file
     */
    fun getFileName(filePath: String?): String {
        filePath?.takeUnless { isSpace(filePath) }
                ?.let {
                    val lastSep = filePath.lastIndexOf(File.separator)
                    return if (lastSep == -1) filePath else filePath.substring(lastSep + 1)
                }

        return ""
    }

    /**
     * Return the name of file without extension.
     *
     * @param file The file.
     * @return the name of file without extension
     */
    fun getFileNameNoExtension(file: File?): String {
        return if (file == null) "" else getFileNameNoExtension(file.path)
    }

    /**
     * Return the name of file without extension.
     *
     * @param filePath The path of file.
     * @return the name of file without extension
     */
    fun getFileNameNoExtension(filePath: String?): String {
        filePath?.takeUnless { isSpace(filePath) }
                ?.let {
                    val lastPoi = filePath.lastIndexOf('.')
                    val lastSep = filePath.lastIndexOf(File.separator)
                    if (lastSep == -1) {
                        return if (lastPoi == -1) filePath else filePath.substring(0, lastPoi)
                    }
                    return if (lastPoi == -1 || lastSep > lastPoi) {
                        filePath.substring(lastSep + 1)
                    } else filePath.substring(lastSep + 1, lastPoi)
                }

        return ""
    }

    /**
     * Return the extension of file.
     *
     * @param file The file.
     * @return the extension of file
     */
    fun getFileExtension(file: File?): String {
        return if (file == null) "" else getFileExtension(file.path)
    }

    /**
     * Return the extension of file.
     *
     * @param filePath The path of file.
     * @return the extension of file
     */
    fun getFileExtension(filePath: String?): String {
        filePath?.takeUnless { isSpace(filePath) }
                ?.let {
                    val lastPoi = filePath.lastIndexOf('.')
                    val lastSep = filePath.lastIndexOf(File.separator)
                    return if (lastPoi == -1 || lastSep >= lastPoi) "" else filePath.substring(lastPoi + 1)
                }

        return ""
    }

    private fun copyOrMoveDir(srcDir: File?, destDir: File?, isMove: Boolean): Boolean {
        return copyOrMoveDir(srcDir, destDir, isMove, object : OnReplaceListener {
            override fun onReplace(): Boolean {
                return true
            }
        })
    }

    private fun copyOrMoveDir(srcDir: File?, destDir: File?, isMove: Boolean, listener: OnReplaceListener?): Boolean {
        ConditionalUtils.allNotNull(srcDir, destDir) { return@allNotNull it }
                ?.takeIf { it[0].exists() && it[0].isDirectory }
                ?.takeUnless { (it[1].path + File.separator).contains(it[0].path + File.separator) }
                ?.let {
                    if (it[1].exists()) {
                        if (listener == null || listener.onReplace()) {// require delete the old directory
                            if (!deleteAllInDir(it[1])) {// unsuccessfully delete then return false
                                return false
                            }
                        } else {
                            return true
                        }
                    }

                    it
                }
                ?.takeIf { createDir(it[1]) }
                ?.let {
                    val destPath = it[1].path + File.separator

                    it[0].listFiles()?.let { files ->
                        files.forEach { file ->
                            val oneDestFile = File(destPath + file.name)
                            if (file.isFile) {
                                if (!copyOrMoveFile(file, oneDestFile, listener, isMove))
                                    return false
                            } else if (file.isDirectory) {
                                if (!copyOrMoveDir(file, oneDestFile, isMove, listener))
                                    return false
                            }
                        }
                    }

                    return !isMove || deleteDir(it[0])
                }

        return false
    }

    private fun copyOrMoveFile(srcFile: File?, destFile: File?, isMove: Boolean): Boolean {
        return copyOrMoveFile(srcFile, destFile, object : OnReplaceListener {
            override fun onReplace(): Boolean {
                return true
            }
        }, isMove)
    }

    private fun copyOrMoveFile(srcFile: File?, destFile: File?, listener: OnReplaceListener?, isMove: Boolean): Boolean {
        ConditionalUtils.allNotNull(srcFile, destFile) { return@allNotNull it }
                ?.takeUnless { it[0] == it[1] }
                ?.takeIf { it[0].exists() && it[0].isFile }
                ?.let {
                    if (it[1].exists()) {
                        if (listener == null || listener.onReplace()) {// require delete the old file
                            if (!destFile!!.delete()) {// unsuccessfully delete then return false
                                return false
                            }
                        } else {
                            return true
                        }
                    }

                    it
                }
                ?.takeIf { createDir(it[1].parentFile) }
                ?.let {
                    return try {
                        writeFileFromIS(it[1], FileInputStream(it[0])) && !(isMove && !deleteFile(it[0]))
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                        false
                    }
                }

        return false
    }

    private fun byte2FitMemorySize(byteNum: Long): String {
        return when {
            byteNum < 0 -> "shouldn't be less than zero!"
            byteNum < 1024 -> String.format(Locale.getDefault(), "%.3fB", byteNum.toDouble())
            byteNum < 1048576 -> String.format(Locale.getDefault(), "%.3fKB", byteNum.toDouble() / 1024)
            byteNum < 1073741824 -> String.format(Locale.getDefault(), "%.3fMB", byteNum.toDouble() / 1048576)
            else -> String.format(Locale.getDefault(), "%.3fGB", byteNum.toDouble() / 1073741824)
        }
    }

    private fun isSpace(s: String?): Boolean {
        if (s == null) return true
        var i = 0
        val len = s.length
        while (i < len) {
            if (!Character.isWhitespace(s[i])) {
                return false
            }
            ++i
        }
        return true
    }

    private fun writeFileFromIS(file: File, inputStream: InputStream): Boolean {
        var os: OutputStream? = null
        try {
            os = BufferedOutputStream(FileOutputStream(file))
            val data = ByteArray(8192)
            var len: Int
            len = inputStream.read(data, 0, 8192)
            while (len != -1) {
                os.write(data, 0, len)

                len = inputStream.read(data, 0, 8192)
            }
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            try {
                os?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun bytes2HexString(bytes: ByteArray?): String {
        val strBuff = StringBuffer()

        bytes?.takeIf { bytes.isNotEmpty() }
                ?.forEach { strBuff.append(byte2Hex(it)) }
                ?.let { return strBuff.toString().toUpperCase() }

        return ""
    }

    private fun byte2Hex(byte: Byte): String {
        return "${((byte.toInt() shr 4) and 0x0F).toString(16)}${(byte and 0x0F).toString(16)}"
    }
}