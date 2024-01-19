package com.kotlin.coroutine.structure

import com.kotlin.coroutine.basic.printWithThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(1000)
        printWithThread("job 1")
    }

    // join 은 suspend fun 이기 때문에 다른 suspend fun 에서만 실행시킬 수 있다.
    // 때문에 main 을 suspend fun 으로 만들면 된다.
    job.join()
}

fun scope1() {
    CoroutineScope(Dispatchers.Default).launch {
        delay(1000)
        printWithThread("job 1")
    }

    // runBlocking 이 없을 땐 스레드를 넉넉히 멈춰줘야한다.
    // 원래는 runBlocking 이 본인 안에서 실행 된 다른 코루틴이 끝날 때 까지 스레드를 blocking 해준다.
    // 하지만 지금은 그 기능이 없기 때문에 직접 sleep 을 해줘야한다.
    Thread.sleep(1500)
}