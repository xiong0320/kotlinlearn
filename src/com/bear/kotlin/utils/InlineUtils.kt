package com.bear.kotlin.utils

class InlineUtils {
    inline fun test1(l:()->Unit){
        l.invoke()
        return
    }
    inline fun test2(crossinline l:()->Unit) {
        l.invoke()
        return
    }
    inline fun test3(l0:()->Unit,noinline l:()->Unit): ()->Unit{
        l0.invoke()
        l.invoke()
        println("test3")
        return l
    }
}