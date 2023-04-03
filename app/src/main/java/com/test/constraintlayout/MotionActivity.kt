package com.test.constraintlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent




/**
 * @author:
 * @created on: 2023/3/20 17:10
 * @description:
 */
class MotionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_layout)
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("data", "要处理的数据")
        startService(intent)

    }
}