package com.bear.kotlin.utils

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import java.util.concurrent.Semaphore
import java.util.concurrent.ExecutorService
import java.util.concurrent.CountDownLatch
import com.bear.kotlin.thread.AtomicExample




class ThreadUtil {
    fun testFutureTask() {
        val futureTask = FutureTask(Callable {
            var result = 0
            for (i in 0..999) {
                Thread.sleep(10)
                result += i
            }
            result
        })

        val computeThread = Thread(futureTask)
        computeThread.start()

        val otherThread = Thread {
            println("other task is running...")
            try {
                Thread.sleep(1000)
                println("other task is finished")
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        otherThread.start()
        for (i in 0 ..30) {
            println("Main Thread Run before...")
            Thread.sleep(100)
        }
        println(futureTask.get())
        for (i in 0 ..30) {
            println("Main Thread Run after...")
            Thread.sleep(100)
        }
    }
    fun testSemaphore() {
        val clientCount = 3
        val totalRequestCount = 20
        val semaphore = Semaphore(clientCount)
        val executorService = Executors.newCachedThreadPool()
        for (i in 0 until totalRequestCount) {
            executorService.execute {
                try {
                    print( "Thread = "+Thread.currentThread().id+" ")
                    semaphore.acquire()
                    println(semaphore.availablePermits().toString() + " ")
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    semaphore.release()
                }
            }
        }
        executorService.shutdown()
    }
    fun testAtomicExample() {
        val threadSize = 1000
        val example = AtomicExample() // 只修改这条语句
        val countDownLatch = CountDownLatch(threadSize)
        val executorService = Executors.newCachedThreadPool()
        for (i in 0 until threadSize) {
            executorService.execute {
                example.add()
                countDownLatch.countDown()
            }
        }
        countDownLatch.await()
        executorService.shutdown()
        println(example.get())
    }
}