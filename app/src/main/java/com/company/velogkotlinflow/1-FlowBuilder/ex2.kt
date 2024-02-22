package com.company.velogkotlinflow.`1-FlowBuilder`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.random.Random

// Flow 취소 : withTimeoutOrNull(???L) : ???L이 지나면 flow의 emit을 취소 시키고 null을 반환한다.
//          : withTimeout을 통해서 취소 시킬 수 있는데, 이때에는 예외처리를 통해서 flow 취소를 다뤄주면 된다.

fun flowSomething2() : Flow<Int> = flow {
    repeat(10) {
        emit(Random.nextInt(0 , 500))
        delay(100L)
    }
}

fun main() = runBlocking {
    val result = withTimeoutOrNull(500L) {
        flowSomething2().collect{value ->
            println(value)
        }
        true
    } ?: false
    if (!result) {
        println("취소 되었습니다.")
    }
}


//fun main() = runBlocking {
//    val result = try {
//        withTimeout(500L) {
//            flowSomething2().collect { value ->
//                println(value)
//            }
//        }
//    } finally {
//        println("취소 되었습니다.")
//
//    }
//
//}