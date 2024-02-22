package com.company.velogkotlinflow.`8-FlowCompleted`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

// onCompletion : 선언적으로 완료 처리하기

// onCompletion { 이 onCompletion 코드 블록 내부에 완료 된 후 실행 될 코드 작성하면 된다. }
fun simple2() : Flow<Int> = (1..3).asFlow()

fun main() = runBlocking {
    simple2()
        .map {
            if (it > 2) {
                throw IllegalStateException()
            }
            it + 1
        }
        .catch { e-> emit(-99) }
        .onCompletion { println("Done") }
        .collect { value -> println(value) }
}


