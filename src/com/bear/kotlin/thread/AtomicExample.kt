package com.bear.kotlin.thread

import java.util.concurrent.atomic.AtomicInteger



class AtomicExample {
    private val cnt = AtomicInteger()

    fun add() {
        cnt.incrementAndGet()
    }

    fun get(): Int {
        return cnt.get()
    }
}