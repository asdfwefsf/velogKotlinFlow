package com.company.velogkotlinflow.`7-FlowException`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

// Flow에서 어디서 발생하든 상관없이 모든 예외는 처리가 가능하다.

fun simple2(): Flow<String> =
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i)
        }
    }
    .map {
        check(it <= 1) { "Crashed on $it" }
        "string $it"
    }

fun main() = runBlocking<Unit> {
    try {
        simple2().collect { value -> println(value) }
    } catch (e : Throwable) {
        println("Caught $e")
    }
}