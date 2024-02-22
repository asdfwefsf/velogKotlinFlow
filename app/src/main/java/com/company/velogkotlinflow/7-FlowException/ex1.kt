package com.company.velogkotlinflow.`7-FlowException`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

// Flow 예외 처리 1 : 수집측에서 예외처리하기. -> try~catch 구문 활용해서

fun simple() : Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    try {
        simple().collect {value ->
            println(value)
            check(value <= 1) { "Collected $value"}
        }
    } catch (e : Throwable) {
        println("Caught $e")
    }
}

