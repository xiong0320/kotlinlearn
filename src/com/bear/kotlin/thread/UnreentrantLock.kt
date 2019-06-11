package com.bear.kotlin.thread

import java.util.concurrent.atomic.AtomicReference

class UnreentrantLock {
    private val owner = AtomicReference<Thread>()
    public fun lock() {
        val current = Thread.currentThread()
        while (true) {
            if (!owner.compareAndSet(null,current)) {
                println("return")
                return
            }
            println("lock compareAndSet = true")
        }
    }
    public fun unlock() {
        val current = Thread.currentThread()
        val isb = owner.compareAndSet(current,null)
        println(isb)
    }
}