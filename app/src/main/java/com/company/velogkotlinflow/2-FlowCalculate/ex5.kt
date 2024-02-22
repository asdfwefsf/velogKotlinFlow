package com.company.velogkotlinflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

// Flow + take : Flow 요소의 일부만 취할 수 있다. (기준은 처음부터 잡고 특정 개수의 'Flow 요소'만 취한다.)

suspend fun someCalc5(i: Int): Int {
    delay(100L)
    return i * 2
}

fun main() = runBlocking {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc5(it))
    }.take(7)
        .collect {
            println(it)
        }
}