package com.kotlin.coroutine.basic

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    // 코루틴 구속 요소와 발생한 에외를 쓰지 않기 위해 _ 처리
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        printWithThread("예외")
        throw throwable
    }

    val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
        throw IllegalArgumentException()
    }

    delay(1000)
}

fun le1():Unit = runBlocking {
    val job = launch {
        try {
            throw IllegalArgumentException()
        } catch (e: IllegalArgumentException) {
            printWithThread("정상 종료")
        }
    }
}
