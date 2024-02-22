package com.company.velogkotlinflow.`4-FlowBuffering`


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// Flow + collectLatest : Flow 요소를 받아서 처리하는 와중에 새로운 값이 오면은 이전에 처리하던 내용들을 다 리셋하고 새로운 값만 처리한다. 그래서 결과적으로는 Flow의 마지막 요소만 처리한다.
// 마지막 값이 올 때까지 기다리는 것이 아니라 Flow 요소들을 처리하는 와중에 새로운 Flow 요소가 들어오면 기존에 처리하고 있던 Flow 요소와 관련된 모든 작업을 리셋하는 방식으로
// 마지막 Flow 요소만 처리하는 것이야.

// collectLatest는 코드 내부적으로 buffer를 사용하고 있으므로 , buffer의 기능을 사용하고 있다.

fun simple4(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // (100) 1 (200) 2 (300) 3 (400) 4 (500) 5 (600)
        emit(i)
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple4().collectLatest { value -> // 1 , 2 // 리셋 // 2 , 3 // 리셋 // 3만 처리
            println("값 ${value}를 처리하기 시작합니다.")
            delay(300)
            println(value)
            println("처리를 완료하였습니다.")
        }
    }
    println("Collected in ${time} ms")
}