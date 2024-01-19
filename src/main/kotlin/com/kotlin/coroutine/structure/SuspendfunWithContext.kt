package com.kotlin.coroutine.structure

import com.kotlin.coroutine.basic.printWithThread
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    printWithThread("start")
    printWithThread(calculateResult2())
    printWithThread("end")
}

suspend fun calculateResult2(): Int = withContext(Dispatchers.Default) {
    val num1 = async {
        delay(1000)
        10
    }

    val num2 = async {
        delay(1000)
        20
    }

    num1.await() + num2.await()
}