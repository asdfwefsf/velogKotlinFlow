package com.company.velogkotlinflow

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

// Flow + first : Flow에서 방출된 요소들 중 첫번째 요소만 반환한다. :  만약 Flow가 요소를 방출하지 않으면 "NoSuchElementException" 에러를 발생시킨다.
// 따라서 Flow의 첫번째 요소만 알고 싶을때 사용.

fun main() = runBlocking {
    val emptyValue = flowOf<Int>()
    val firstValue = flowOf(99, 2, 3)
    try {
        println(emptyValue.first())
    } catch (e: NoSuchElementException) {
        println("Flow에 요소가 없어서 예외가 발생했습니다: ${e.message}")
    }

}