package com.kotlin.coroutine.basic

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val job = async(SupervisorJob()) {
        throw IllegalArgumentException()
    }

    delay(1000)
}

fun exExample3(): Unit = runBlocking {
    val job = async {
        throw IllegalArgumentException()
    }

    delay(1000)
}

fun exExample2(): Unit = runBlocking {
    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException()
    }

    delay(1000)
    job.await()
}

fun exExample1(): Unit = runBlocking {
    val job = CoroutineScope(Dispatchers.Default).launch {
        throw IllegalArgumentException()
    }

    delay(1000)
}