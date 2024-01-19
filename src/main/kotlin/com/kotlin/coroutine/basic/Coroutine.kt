package com.kotlin.coroutine.basic

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main(): Unit = runBlocking {
    printWithThread("start")
    launch {
        newRoutine2()
    }
    yield()
    printWithThread("end")
}

suspend fun newRoutine2() {
    val num1 = 1
    val num2 = 2
    yield()
    printWithThread("${num1 + num2}")
}

fun printWithThread(str: Any?) {
    println("[${Thread.currentThread().name} $str]")
}