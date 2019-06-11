package com.bear.kotlin.utils

class TemplateUtils {
    class Test<T> where T:Callback,T:Runnable{
        fun add(t:T) {
            t.run()
            t.callback()
        }
    }
    open class A:Runnable{
        override fun run() {
            println("A run")
        }
    }
    class B:Callback,A(){
        override fun callback() {
            println("B callback")
        }

    }
}
interface Callback {
    fun callback()
}
class View<T>(val clazz:Class<T>) {
    val presenter by lazy { clazz.newInstance() }
    companion object {
        inline operator fun <reified T> invoke() = View(T::class.java)
    }
}
class Presenter {
    override fun toString(): String {
        return "presenter"
    }
}
fun testMvp() {
    val b = View<Presenter>().presenter
    val a = View.Companion.invoke<Presenter>().presenter
    println(a)
    println(b)
}