package com.company.velogkotlinflow.`6-FlowFlattening`

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// Flow + flatMapMerge : 첫 요소의 Flattening을 시작하고 바로 이어서 다음 요소의 Flattening을 시작한다.

// flatMapMerge 또한 flatMapConcat과 동일하게 각각의 Flow 요소에 대해서 새로운 FlowC를 만들어서 방출한다. 하지만 , 순차적으로 방출하는 것이 아니라 병렬적으로 Flow를 방출한다. : .flatMapMerge의 의미에 영향을 받는것은 FlowB이다.
// 즉, FlowB가 처리하는 작업이 병렬적으로 진행되어 다른 요소들의 처리를 기다리지 않는다. (이런 점이 flatMapConcat과의 차이점이다.)
// FlowA.flatMapMerge { FlowB } : FlowA가 방출한 값을 FlowB에서 새로운 FlowC로 반환하는데, FlowC가 방출한 요소가 종단 연산자에서 처리 완료 될 때까지 기다리지 않고,
// 바로 이어서 FlowC는 새로운 요소를 병렬적으로 방출한다. : 들어오는 족족 FlowB에서 처리해서 FlowC로 방출.(병렬적),



fun requestFlow2(i : Int) : Flow<String> = flow {
    emit("$i : First")
    delay(500)
    emit("$i : Second")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapMerge {
            requestFlow2(it)
        }
        .collect {
            println("$it at ${System.currentTimeMillis() - startTime} ms from start")
        }
}