package com.company.velogkotlinflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

// Flow + transform : Flow의 요소들을 복잡한 전환 작업을 통해서 새로운 Flow 요소로 변환하여 방출 할 수 있다. (Flow 표준 연산자들 보다 더 확장적인 연산을 가능하게 한다.)

// 1. Flow 요소를 사용자 정의로 자유롭게 변환 가능 == 조건문 등 여러 로직 적용이 가능해
// 2. 여러 값을 emit() 할 수 있다. == 한턴에 emit()을 여러 번 호출 할 수 있어

suspend fun someCalc(i : Int) : Int {
    delay(100L)
    return i * 2
}

fun main() = runBlocking {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc(it))
    }.collect {
        println(it)
    }
}