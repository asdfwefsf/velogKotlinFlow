package com.company.velogkotlinflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

// Flow + takeWhile : takeWhile의 코드 블락 내부에 있는 조건을 만족하는 동안에만 emit() 할 수 있다.

suspend fun someCalc6(i: Int): Int {
    delay(100L)
    return i * 2
}

fun main() = runBlocking {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc6(it))
    }.takeWhile {
        it < 15
    }.collect{
        println(it)
    }
}