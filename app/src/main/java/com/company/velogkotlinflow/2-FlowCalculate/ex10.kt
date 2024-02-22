package com.company.velogkotlinflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

// Flow + fold : 초기값이 있다는 것만 제외하면 reduce 연산자와 동일한 기능을 한다.

fun main() = runBlocking {
    val value = (1..10)
        .asFlow()
        .fold(10) { a , b ->
            a + b
        }
    println(value)
}