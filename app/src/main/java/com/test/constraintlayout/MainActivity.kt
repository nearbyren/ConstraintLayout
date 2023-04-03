package com.test.constraintlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import androidx.constraintlayout.widget.Placeholder
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.constraint_layout_12_flow)


        //点击a 则移动至指定位置
        val a = findViewById<TextView>(R.id.A)
        val p = findViewById<Placeholder>(R.id.placeholder)
        a.setOnClickListener {
//            p.setContentId(R.id.A)
            val blockingQueue = LinkedBlockingDeque<String>()
            blockingQueue.add("1")
            blockingQueue.add("2")
            blockingQueue.add("3")
            blockingQueue.add("4")
            blockingQueue.add("5")
            MessageConsumer(blockingQueue).start()
        }
        startActivity(Intent(this, MotionActivity::class.java))
    }

    class MessageConsumer(private val messageQueue: BlockingQueue<String>) {

        private val handler: Handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                // 处理消息
                val message = msg.obj as String
                println("GO Received message: $message")
            }
        }

        fun start() {
            Thread {
                while (true) {
                    try {
                        val message = messageQueue.take()
                        val msg = handler.obtainMessage(0, message)
                        msg.sendToTarget()
                        println("GO send message: $message size = ${messageQueue.size}")
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                        println("GO send message: ${e.message}")
                    }

                }
            }.start()
        }
    }
}