package com.company.velogkotlinflow.`9-FlowLaunching`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// Flow + launchIn : launchIn을 이용하면 별도의 코루틴에서 Flow를 런칭 할 수 있다.
// 이렇게 되면 Flow가 비동기적으로 실행되기 때문에 MainThread에서 코드가 멈추지 않는다.

fun events2() : Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking {
    events2()
        .onEach { event -> println("Event : $event ${Thread.currentThread().name}") }
        .launchIn(this) // launchIn()의 parameter로 CoroutineScope를 받으므로, 상위 Coroutine의 Context를 이해를 하고 동작을 한다.
                              // launchIn()은 새로운 Coroutine Scope를 만들어 낸다. : 새로운 Scope를 만들어 낸 후 해당 Scope에서 onEach를 돌린다.
                              // 따라서 해당 Scope에서 UI 작업|| 네트워크 호출 등의 이벤트 처리 작업을 진행해도 된다. 애는 별개의 Coroutine에서 별개의 Thread에서 이벤트를 관찰하면서 동작을 하는거야.
                              // 반드시 launchIn()을 이용을 해야만 Flow에서 이벤트 처리를 할 수 있다.(사실상 Flow에 별도의 Coroutine Scope를 만들어서 거기서 실행 하는거야.)
    println("Done ${Thread.currentThread().name}")
}