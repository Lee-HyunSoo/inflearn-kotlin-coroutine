package com.kotlin.coroutine.structure

import com.kotlin.coroutine.basic.printWithThread
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    printWithThread("start")
    printWithThread(calculateResult())
    printWithThread("end")
}

suspend fun calculateResult(): Int = coroutineScope {
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