package com.company.velogkotlinflow.`1-FlowBuilder`

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

// flow{} 이외의 플로우 빌더 : asFlow : 컬렉션||시퀀스를 전달해 플로우를 만들 수 있다. + 별도로 emit 할 필요없어 내부에 emit 해주는 코드가 있어.

fun main() = runBlocking {
    println("asFlow")
    listOf(1, 2, 3, 4, 5).asFlow().collect { value ->
        println(value)
    }
    (6..10).asFlow().collect {
        println(it)
    }
    println("flowOf")
    flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect{ println(it) }
    println("flow")
    flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
        emit(6)
        emit(7)
        emit(8)
        emit(9)
        emit(10)
    }.collect { println(it) }
}