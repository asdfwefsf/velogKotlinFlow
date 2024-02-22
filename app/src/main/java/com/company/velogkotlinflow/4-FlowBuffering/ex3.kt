package com.company.velogkotlinflow.`4-FlowBuffering`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// Flow + Conflate : 중간의 값을 융합(conflate) 할 수 있다. 내가 처리하지 못하고 있던 사이에 받았던 값들을 다 버린다.

// conflate는 buffer를 상속 받았으므로 , 방출과 수집이 독립적으로 일어난다. 즉, buffer에 기능이 추가 되었다고 생각해도 좋다.
// public fun <T> Flow<T>.conflate(): Flow<T> = buffer(CONFLATED)

// conflate()는 빠른 방출 속도와 느린 소비 속도 사이의 갭차를 해결하는 기능을 한다. 주로 방출의 속도가 소비의 속도 보다 빠를 때 사용한다.
// 'Flow가 요소를 방출하는 속도'가 '방출된 요소가 소비되는 속도'보다 빠른 경우에, 처리되지 않은 요소들을 버려버린다.
// 즉, 종단 연산자가 요소를 처리하는 것이 준비 될 때까지 기다리지 않고, 준비가 안 되어있으면 그냥 해당 요소를 버려버린다.
// 이로 인해 항상 최신의 값을 소비 할 수 있는 장점이 있으며, 주로 실시간 데이터처리||빠른 데이터 스트림 처리에서 사용한다.

fun simple3() : Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // (100) 1 (200) 2 (300) 3 (400) 4 (500) 5 (600)
        emit(i)
    }
}

fun main() = runBlocking {
    val time=  measureTimeMillis {
        simple3().conflate()
            .collect { value ->
                delay(300)
                println(value)
            }
    }
    println("Collected in ${time} ms")
}