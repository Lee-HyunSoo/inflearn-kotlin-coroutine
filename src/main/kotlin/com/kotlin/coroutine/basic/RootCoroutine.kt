package com.kotlin.coroutine.basic

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    // 코루틴을 만들 시 새로운 영역에 만들고, 메인 스레드가 아닌 다른 영역에서 실행시킨다.
    val job1 = CoroutineScope(Dispatchers.Default).launch {
        delay(1000L)
        printWithThread("job 1")
    }

    val job2 = CoroutineScope(Dispatchers.Default).launch {
        delay(1000L)
        printWithThread("job 2")
    }
}

fun rootExample1(): Unit = runBlocking {
    val job1 = launch {
        delay(1000L)
        printWithThread("job 1")
    }

    val job2 = launch {
        delay(1000L)
        printWithThread("job 2")
    }
}