package com.bear.kotlin.task

class SleepRunnable :Runnable {
    override fun run() {
        val thread = Thread.currentThread()
        val name = thread.name
        println(name)
        Thread.sleep(2000)
    }
}