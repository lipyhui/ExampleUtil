package com.example.util

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.lipy.elog.ELog

import com.android.lipy.utils.operation.ConditionalUtils
import com.android.lipy.utils.file.FileUtils
import com.android.lipy.utils.operation.ConditionalUtils.allNotNull

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ELog.e("############### START ############################################")

        conditionalUtilTest()
    }

    private fun conditionalUtilTest() {
        FileUtils.getFileByPath("")

        val ifAll = allNotNull("ss", "dd") {
            ELog.e("############### ifAllNotNull ${it[0]}, ${it[1]}")
            "@@@ if all value are not null !!!!!!!!!!!!!!!!!!!!!!!!!!!"
        }
        ELog.e("############### ifAllNotNull $ifAll")

        val ifAny = ConditionalUtils.anyNull("sss", "bb", null, "ss", "Ff", "sss", "sss", "dddd", "fffff") {
            ELog.e("############### anyNull any null value")
            return@anyNull "@@@ if any value is null ??????"
        }
        ELog.e("############### anyNull $ifAny")

        JavaTest.conditionalUtilTest()
    }
}
