package com.kotlin.coroutine.basic

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val job = launch {
        try {
            delay(1000)
        } catch (e: CancellationException) {
            // 아무것도 안한다.
        } finally {
            // 필요한 자원을 닫을 수도 있다.
        }

        printWithThread("delay에 의해 취소되지 않았다.") // 출력되지 않아야하는데 출력된다.
    }
    delay(100)
    printWithThread("취소 시작")
    job.cancel()
}

fun delayExample2(): Unit = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread("${i++}번 째 출력!")
                nextPrintTime += 1000 // 1초 더해주기
            }

            // 코루틴은 내부에서 isActive 라는 프로퍼티로 자신의 상태를 확인할 수 있다.
            // 이를 통해 launch 내부의 코루틴이 현재 취소 명령을 받았는지 여전히 활성화 상태인지 알 수 있다.
            if (!isActive) {
                // 여기서 끝내면 취소가 되지 않는다.
                // launch 입장에서 본인이 취소가 되었는지 아닌지 알아도 job.cancel() 이 아직 실행되지 않았기 때문
                // 이 스레드가 cancel() 을 실행하거나, cancel() 을 실행하기 위한 다른 스레드가 필요하다.
                throw CancellationException()
            }
        }
    }

    delay(100)
    job.cancel()
}

fun delayExample1(): Unit = runBlocking {
    val job1 = launch {
        delay(10)
        printWithThread("job 1")
    }

    val job2 = launch {
        delay(1000L)
        printWithThread("job 2")
    }

    delay(100)
    job1.cancel()
}