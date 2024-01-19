package com.kotlin.coroutine.basic

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun example4(): Unit = runBlocking {
    val job1 = launch {
        delay(1000)
        printWithThread("job 1")
    }

    job1.join()

    val job2 = launch {
        delay(1000)
        printWithThread("job 2")
    }
}

fun example3(): Unit = runBlocking {
    val job = launch {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }

    delay(1000L)
    job.cancel() // 취소
}

fun example2(): Unit = runBlocking {
    // 코루틴은 원래 만든 즉시 언제든 실행시킬 수 있다.
    // 그래서 명확한 시작신호를 줄 때까지 대기 시키는 코드가 존재한다.
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread("hello launch")
    }

    delay(1000L)
    job.start()
}