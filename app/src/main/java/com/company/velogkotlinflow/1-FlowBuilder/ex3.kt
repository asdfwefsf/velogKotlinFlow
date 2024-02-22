package com.company.velogkotlinflow.`1-FlowBuilder`

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

// flow{} 이외의 플로우 빌더 : flowOf는 별도로 emit 해줄 필요가 없어 내부에 emit 해주는 코드가 존재해

fun main() = runBlocking {
    println("flowOf")
    flowOf(1, 2, "3", 4, 5).collect { value ->
        println(value)
    }
    println("flow")
    flow {
       emit(1)
       emit(2)
       emit(3)
       emit(4)
       emit(5)
    }.collect { value ->
        println(value)
    }
}
