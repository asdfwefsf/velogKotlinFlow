package com.company.velogkotlinflow

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.runBlocking

// Flow + toSet : Flow의 요소들을 set 형태로 반환한다.

// toSet : Flow 요소의 순서는 유지되지 않고, 중복된 요소는 제거하고 Set으로 반환한다.
// 따라서 순서가 중요하지 않고 , 유일한 값들만 포함시키고 싶을 때 반환한다.

fun main() = runBlocking {
    val numbersFlow = flowOf(1, 2, 2, 4,  3, 4, 5)
    val numbersList = numbersFlow.toSet()
    println(numbersList)
}