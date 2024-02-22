package com.company.velogkotlinflow.`9-FlowLaunching`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// 이벤트로 Flow를 처리하기 => Flow + event : addEventlistener 대신 Flow의 onEach를 사용 할 수 있다. 이벤트마다 onEach가 대응하는 것이다.
// Flow에서는 onEach를 통해서 어떠한 이벤트를 처리 할 수 있다. : Listener를 설정해서 콜백을 등록하는 것보다 훨씬 간편한다.
// 그러나 여기에는 한가지 문제점이 있다. collect가 Flow의 스르림이 끝날때까지 기다린다는 것이다. 그래서 이벤트를 감시하는것 때문에 메인 스레이 드에서 코드 진행이 멈추게 된다. 이벤트는 끝날 일 이 없기 때문에
// 그래서 ex2에서 이에 대한 해결책이 있다.

// 결론 : collect{}를 이벤트를 위해서 사용 할 수 없다. 메인 스레드에서 collect{} 때문에 코드 진행이 멈춘다.

fun events() : Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking {
    events()
        .onEach { event -> println("Event : $event") }
        .collect {

        }
    println("Done")
}