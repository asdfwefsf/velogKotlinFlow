package com.company.velogkotlinflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// Flow + flatMapLatest : 다음 요소의 플레트닝을 시작하며 이전에 진행 중이던 플레트닝을 취소한다.

// FlowA에서 FlowB로 요소를 넘길 때 기존에 넘겨진 FlowA의 요소가 있다면 [FlowB가 FlowA의 요소를 받아서 처리하는 작업을 취소 시키고] , [FlowA의 새로운 요소만 받아서 FlowB에서 처리한다.]


fun requestFlow3(i : Int) : Flow<String> = flow {
    emit("$i : First")
    delay(500)
    emit("$i : Second")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapLatest {
            requestFlow3(it)
        }
        .collect {
            println("$it at ${System.currentTimeMillis() - startTime} ms from start")
        }
}