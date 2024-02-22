package com.company.velogkotlinflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking

// Flow + count : 술어를 만족하는 Flow 요소의 갯수를 세서 알려준다.

fun main() = runBlocking {
    val counter = (1..10)
        .asFlow()
        .count{
            (it % 2) == 0
        }
    println(counter)
}