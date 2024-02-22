package com.company.velogkotlinflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

// Flow + dropWhile : dropWhile의 코드 블락 내부에 있는 조건에 맞지 않을 때까지 Flow 요소들을 다 버리고 , 조건이 처음으로 맞지 않는 순간 부터 해당 요소와 나머지 요소들을 모두 방출

suspend fun someCalc8(i: Int): Int {
    delay(100L)
    return i * 2
}

fun main() = runBlocking {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc8(it))
    }.dropWhile {
        it < 15
    }.collect{
        println(it)
    }
}