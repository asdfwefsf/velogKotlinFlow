package com.company.velogkotlinflow.`6-FlowFlattening`

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// Flow Flattening이란, 플로우 평탄화라고하며 한 플로우의 각 요소를 다른 플로우로 변환하는 것을 말한다. (Flow에서 Flow로 요소를 넘기는 경우) <-> Flow에서 종단 연산자로 요소를 넘기는 경우
// Flow Flattening : 하나의 Flow가 다른 Flow를 방출하는 것을 의미한다. 주로 사용되는 연산자는 flatMapConcat , flatMapMerge , flatMapLatest 3가지이다.
// 즉 , (1)Flow가 방출 한 값을 (2)Flow가 받아서 (3)다른 Flow로 방출하면 (4)임의의 종단 연산자에서 해당 Flow 요소를 처리하는 방법론에 따라서 3가지로 분류된다.

// Flow에서 Flow로 요소를 넘길때는 [받는 Flow가 준비가 되야지 넘길수 있거나] [준비가 안되도 넘길수 있거나] [최신의 Flow가 넘겨질때마다 기존의 Flow는 취소 시킬 수 있거나] 이 세 가지의 종류

// Flow + flatMapConcat : 첫번째 요소에 대해서 Flattening 하고 나서 두번째 요소를 합친다. 즉, '순서대로 합친다'라고 생각하면 된다. (순차적인 처리가 필요할 때 사용한다.)
// cf. concat은 일종의 '이어붙히기'를 의미한다. 아래 코드 참고해서 설명해보면 requestFlow(1)가 호출된 결과 뒤에 requestFlow(2)가 호출된 결과 뒤에 requestFlow(3)을 이어붙힌다.
//

// FlowA.flatMapConcat { FlowB } : FlowA가 방출한 요소(들)을 FlowB에서 받아서 가공해서 새로운 Flow 요소를 순차적으로 방출한다. : 한 Flow의 모든 요소가 처리 될때까지 다음 Flow로 넘어가지 않는다.
// 즉, FlowA와 FlowB를 순차적으로 연결해서 단일 Flow를 만드는 연산자이다. -> flatMapConcat의 의미에 영향을 받는 것은 FlowB이다.
// 꼭 두개의 Flow에만 적용되는 것은 아니며 두개 이상의 Flow들을 순차적으로 연결하여 단일 Flow로 만드는 연산자이다.
// 각각의 Flow에 대해서 별도의 비동기 작업을 수행한 후 그 결과를 단일 Flow로 모아서 방출하고 싶을 때 사용하면 된다. 예를 들면 FlowA의 요소들을 처리하는 FlowB를 만들고.. 이러한 결과를
// 순차적으로 연결하여 최종 결과값을 Flow의 요소로 반환하고자 할 때 사용하면 된다.



fun requestFlow(i : Int) : Flow<String> = flow {
    emit("$i : First")
    delay(500)
    emit("$i : Second")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapConcat {
            requestFlow(it)
        }
        .collect {
            println("$it at ${System.currentTimeMillis() - startTime} ms from start")
        }
}