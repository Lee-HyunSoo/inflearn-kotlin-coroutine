package com.kotlin.coroutine.structure

import com.kotlin.coroutine.basic.printWithThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch {
        delay(600)
        printWithThread("A")
    }

    launch {
        delay(500)
        throw IllegalArgumentException("코루틴 실패!")
    }
}