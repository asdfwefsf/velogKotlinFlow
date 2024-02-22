package com.company.velogkotlinflow.`8-FlowCompleted`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking

// 예외가 발생하거나 Flow의 동작이 끝난 다음에 , 완료된 이후에 동작을 해야 할 필요가 있을 수 있다.
// 이제부터는 그 완료를 어떻게 다루어야 하는지를 알아보겠다.

// 즉, Flow가 요소를 모두 방출하고 난 후 필요한 추가 작업을 어떻게 코딩 할 수 있을지 학습할 것이다.

// 1. 명령형 finally 블록 : 완료를 처리하는 방법 중의 첫번째는 '명령형의 방식으로 finally 블록을 이용하는 것이다.'

fun simple() : Flow<Int> = (1..3).asFlow()

fun main() = runBlocking {
    try {
        simple().collect{ value -> println(value)}
    } finally {
        println("Done")
    }
}