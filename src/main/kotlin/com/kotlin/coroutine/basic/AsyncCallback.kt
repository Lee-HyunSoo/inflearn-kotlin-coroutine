package com.kotlin.coroutine.basic


import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(): Unit = runBlocking {
    val time = measureTimeMillis { // 소요 시간 측정 메서드
        val job1 = async { apiCallback1() }
        val job2 = async { apiCallback2(job1.await()) }
        printWithThread(job2.await())
    }
    printWithThread("소요 시간 : $time ms")
}

suspend fun apiCallback1(): Int {
    delay(1000L)
    return 1
}

suspend fun apiCallback2(num: Int): Int {
    delay(1000L)
    return num + 2
}