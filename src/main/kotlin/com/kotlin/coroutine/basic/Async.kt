package com.kotlin.coroutine.basic

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.boot.util.LambdaSafe.Callback
import kotlin.system.measureTimeMillis

//fun main(): Unit = runBlocking {
//    apiCall1(object : Callback {
//        apiCall2(object : Callback) {
//
//        }
//    })
//}

fun main(): Unit = runBlocking {
    val time = measureTimeMillis { // 소요 시간 측정 메서드
        val job1 = async(start = CoroutineStart.LAZY) { apiCall1() }
        val job2 = async(start = CoroutineStart.LAZY) { apiCall2() }

        job1.start()
        job2.start()
        printWithThread(job1.await() + job2.await())
    }
    printWithThread("소요 시간 : $time ms")
}

// suspend fun 은 또다른 suspend fun 을 부를 수 있다.
suspend fun apiCall1(): Int {
    // delay 가 바로 또다른 suspend fun 이다.
    delay(1000L)
    return 1
}

// suspend fun 은 또다른 suspend fun 을 부를 수 있다.
suspend fun apiCall2(): Int {
    // delay 가 바로 또다른 suspend fun 이다.
    delay(1000L)
    return 2
}

fun example5(): Unit = runBlocking {
    val job = async {
        3 + 5
    }
    val eight = job.await() // await : async 의 결과를 가져오는 함수
    printWithThread(eight)
}