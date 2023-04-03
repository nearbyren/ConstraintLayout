package com.test.constraintlayout

import android.app.Service
import android.content.Intent
import android.os.Handler

import android.os.IBinder

import android.os.HandlerThread
import android.util.Log
import androidx.annotation.Nullable
import java.util.*


/**
 * @author:
 * @created on: 2023/3/21 17:55
 * @description:
 */
class MyService : Service() {
    private var mHandlerThread: HandlerThread? = null
    private var mHandler: Handler? = null

    @Volatile
    private var mIsRunning = false
    private val mQueue: LinkedList<String> = LinkedList()

   override fun onCreate() {
        super.onCreate()
        mHandlerThread = HandlerThread("MyServiceThread")
        mHandlerThread!!.start()
        mHandler = Handler(mHandlerThread!!.looper)
        mIsRunning = true
        mHandler?.post(mRunnable)
    }

    private val mRunnable: Runnable = object : Runnable {
        override fun run() {
            var data: String? = null
            synchronized(mQueue) {
                if (!mQueue.isEmpty()) {
                    data = mQueue.removeFirst()
                }
            }
            if (data != null) {
                // 处理队列中的数据
                Log.d("MyService", "处理数据：$data")
            }
            if (mIsRunning) {
                mHandler?.post(this)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            val data = intent.getStringExtra("data")
            if (data != null) {
                synchronized(mQueue) { mQueue.add(data) }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override  fun onDestroy() {
        super.onDestroy()
        mIsRunning = false
        mHandlerThread!!.quit()
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
