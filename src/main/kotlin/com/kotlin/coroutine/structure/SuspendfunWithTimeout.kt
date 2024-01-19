package com.kotlin.coroutine.structure

import com.kotlin.coroutine.basic.printWithThread
import kotlinx.coroutines.*


fun main(): Unit = runBlocking {
    // null 이 나올수도 있기 때문에 ? 를 써준다.
    val result: Int? = withTimeoutOrNull(1000){// 기다릴 수 있는 시간
        delay(1500)
        10 + 20
    }
    printWithThread(result) // null 이 나온다.
}

fun withTimeout(): Unit = runBlocking {
    val result: Int = withTimeout(1000){// 기다릴 수 있는 시간
        delay(1500)
        10 + 20
    }
    printWithThread(result) // 예외가 터진다.
}

