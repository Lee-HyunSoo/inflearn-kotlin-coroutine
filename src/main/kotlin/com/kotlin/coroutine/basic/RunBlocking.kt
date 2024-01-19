package com.kotlin.coroutine.basic

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        printWithThread("start")
        launch {
            delay(2000L) // yield 와 비슷하다. 특정 시간만큼 멈추고 다른 코루틴으로 넘긴다.
            printWithThread("launch end")
        }
    }
    printWithThread("end") // runBlocking 코드가 모두 끝날 때까지 기다린 후 출력된다.
}