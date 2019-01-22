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

        val mtkPath = "/sdcard/mtklog/"
        val mtkStpFilePath = "/sdcard/mtklog/stp_dump/test.txt"
        val mtkPathNo = "/sdcard/mtklog/ss"
        ELog.e("------------------> isExists ${FileUtils.isExists(filePath = null)}")
        ELog.e("------------------> rename ${FileUtils.rename(File(mtkStpFilePath),"stp_dump_back")}")
    }
}
