package com.test.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.Placeholder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.constraint_layout_12_flow)


        //点击a 则移动至指定位置
        val a = findViewById<TextView>(R.id.A)
        val p = findViewById<Placeholder>(R.id.placeholder)
        a.setOnClickListener {
            p.setContentId(R.id.A)
        }



    }
}