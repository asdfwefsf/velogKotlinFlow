package com.company.velogkotlinflow

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.runBlocking

// Flow + toList : Flow의 요소들을 리스트 형태로 반환한다.

// toList : Flow 요소의 순서를 유지한채로, 중복 허용하여 리스트로 반환한다.
// 따라서 순서가 중요하거나 중복된 요소들도 모두 포함시키고 싶을 때 사용한다.

fun main() = runBlocking {
    val numbersFlow = flowOf(1, 2, 2, 4,  3, 4, 5)
    val numbersList = numbersFlow.toList()
    println(numbersList)
}