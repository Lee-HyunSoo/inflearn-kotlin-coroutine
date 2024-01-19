package com.kotlin.coroutine.structure

import com.kotlin.coroutine.basic.printWithThread
import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() {
    CoroutineName("나만의 코루틴") + Dispatchers.Default
    // 하나의 스레드를 가지고 있는 스레드 풀을 활용해 코루틴 생성
    val threadPool = Executors.newSingleThreadExecutor()
    CoroutineScope(threadPool.asCoroutineDispatcher()).launch {
        printWithThread("새로운 코루틴") // 새로운 코루틴을 만들었던 스레드 풀이 배정해 실행
    }
}

suspend fun element2() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(1000)
        printWithThread("job 1")
        coroutineContext + CoroutineName("이름") // 기존 컨텍스트에 이름 엘리먼트 추가
        coroutineContext.minusKey(CoroutineName.Key) // 엘리먼트의 키를 이용해 컨텍스트 내 데이터 제거
    }

    job.join()
}

fun element1() {
    // 코루틴 컨텍스트의 엘리먼트를 생성 + SupervisorJob 도 하나의 컨텍스트 엘리먼트이다.
    CoroutineName("나만의 코루틴") + SupervisorJob()
    CoroutineName("나만의 코루틴") + Dispatchers.Default
}