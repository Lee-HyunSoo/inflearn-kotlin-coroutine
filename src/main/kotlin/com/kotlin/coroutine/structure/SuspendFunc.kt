package com.kotlin.coroutine.structure

import com.kotlin.coroutine.basic.printWithThread
import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture

fun main(): Unit = runBlocking {
    // deferred 에 대한 의존성 제거
    val result1 = call1()

    val result2 = call2(result1)

    printWithThread(result2)
}

suspend fun call1(): Int {
    // suspend fun 안에서 비동기 라이브러리 혹은 코루틴을 사용하게 변경
    return CoroutineScope(Dispatchers.Default).async {
        Thread.sleep(1000)
        100
    }.await()
}

suspend fun call2(num: Int): Int {
    // suspend fun 안에서 비동기 라이브러리 혹은 코루틴을 사용하게 변경
    return CompletableFuture.supplyAsync {
        Thread.sleep(1000)
        num * 2
    }.await()
}

fun test2(): Unit = runBlocking {
    launch {
        a()
        b()
    }

    launch {
        c()
    }
}

suspend fun a() {
    printWithThread("A")
}

suspend fun b() {
    printWithThread("B")
}

suspend fun c() {
    printWithThread("C")
}

fun test1(): Unit = runBlocking {
    launch {
        delay(1000)
    }
}

interface AsyncCaller {
    suspend fun call()
}

class AsyncCallImpl : AsyncCaller {
    override suspend fun call() {
        TODO("Not yet implemented")
    }
}