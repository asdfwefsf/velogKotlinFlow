package com.company.velogkotlinflow

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking

// Flow + single : Flow가 단 하나의 요소만 방출할 것이라고 예상 될 때 사용한다. : 하나 초과의 요소를 반환하면 "IllegalArgumentException" 에러를 발생시킨다.
// 따라서 Flow가 최대 하나의 요소만 방출하는 것이 확실할 때 사용.
// Flow + singleOrNull : Flow가 단 하나의 요소만 방출하면 해당 요소를 반환하고 , 아무 요소도 방출하지 않거나 , 두개 이상의 요소를 반환하면 'null'을 반환한다.
// 따라서 Flow가 두개 이상의 요소를 방출하거나 아무 요소도 방출하지 않을 것 같을 때 사용

fun main() = runBlocking {
    val singleYes = flowOf(1)
    val singleNo = flowOf(1, 2, 3)
    try {
        println(singleYes.single())
    } catch (e: IllegalArgumentException) {
        println("Flow에 요소가 하나 초과 있어어 예외가 발생했습니다: ${e.message}")
    }
    println(singleYes.singleOrNull())
}