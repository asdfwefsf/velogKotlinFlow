package com.company.velogkotlinflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

// Flow + drop : Flow 요소의 처음 몇개의 요소를 버린다. <-> take와 반대 : take는 처음 몇개의 요소만 방출

suspend fun someCalc7(i: Int): Int {
    delay(100L)
    return i * 2
}

fun main() = runBlocking {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc7(it))
    }.drop(5)
        .collect {
            println(it)
        }
}