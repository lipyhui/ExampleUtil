package com.example.util

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.lipy.elog.ELog

import com.android.lipy.utils.operation.ConditionalUtils
import com.android.lipy.utils.file.FileUtils
import com.android.lipy.utils.operation.ConditionalUtils.allNotNull
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ELog.e("------------------> START ############################################")

        conditionalUtilTest()
        fileUtilTest()
        JavaTest.encryptUtilsTest()
//        JavaTest.ResourceUtilsTest(this);
    }

    /**
     * 逻辑运算ConditionalUtils演示代码
     */
    private fun conditionalUtilTest() {
        ELog.e("------------------> conditionalUtilTest <----------------------")

        val ifAll = allNotNull("ss", "dd") {
            ELog.e("------------------> ifAllNotNull ${it[0]}, ${it[1]}")
            "@@@ if all value are not null !!!!!!!!!!!!!!!!!!!!!!!!!!!"
        }
        ELog.e("------------------> ifAllNotNull $ifAll")

        val ifAny = ConditionalUtils.anyNull("sss", "bb", null, "ss", "Ff", "sss", "sss", "dddd", "fffff") {
            ELog.e("------------------> anyNull any null value")
            return@anyNull "@@@ if any value is null ??????"
        }
        ELog.e("------------------> anyNull $ifAny")

        JavaTest.conditionalUtilTest()
    }

    private fun fileUtilTest() {
        ELog.e("------------------> fileUtilTest <----------------------")

        val mtkPath = "/sdcard/mtklog"
        val mtkStpFilePath = "/sdcard/mtklog/netlog/file_tree.txt"
        val mtkPathNo = "/sdcard/mtklog/mobilelog/file_tree.txt"
        ELog.e("------------------> isExists ${FileUtils.isExists(filePath = null)}")
        ELog.e("------------------> rename ${FileUtils.rename(File(mtkStpFilePath), "stp_dump_back")}")
        ELog.e("------------------> moveDir ${FileUtils.moveDir(mtkPathNo, "/sdcard/mtklog/ss_back/ss3")}")
        ELog.e("------------------> moveFile ${FileUtils.moveFile(mtkStpFilePath, "/sdcard/mtklog/ss_back/test_back.txt")}")
        ELog.e("------------------> getFileExtension ${FileUtils.getFileExtension(mtkPathNo)}")
    }
}
