package com.company.velogkotlinflow.`8-FlowCompleted`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

// onCompletion의 장점 : onCompletion은 종료 처리를 할 때 예외가 발생되었는지 여부를 알 수 있다. -> try~catch~finally 사용하면 catch에서는 Flow의 예외를 알 수 있지만 , finally에서는 알 수 없었던 단점 해결.

// onCompletion{}과 catch{}의 코드 블록에서 받아오는 it은 Flow 처리 중 발생한 예외를 참조한다.

fun simple3() : Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

fun main() = runBlocking {
    simple3()
        .onCompletion { cause -> if (cause != null) println("Flow completed exceptionally") else { println("Flow completed.") } }
        .catch { cause -> println(cause) }
        .collect { value -> println(value) }
}

