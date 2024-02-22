package com.company.velogkotlinflow.`3-FlowContext`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// Flow는 다른 Context로 옮길 수 없다. : 단, flowOn을 통해서는 옮길 수 있다.

// 아래 코드에서 Coroutine의 Context는 Dispatchers.IO이고 , Flow의 Context는 Dispatchers.Default이다.
// 그러나 에러가 발생하게 되는데, 이는 Flow 내부에서는 Context를 변경 할 수 없기 때문이다. : 그래서 fun simple2()에 진입하자마자 에러가 발생 할 것이다.
// 에러 메세지 : Exception in thread "main" java.lang.IllegalStateException: Flow invariant is violated:
// (Flow 내부에서 지켜야 할 규약을 위반하였다는 의미이다. 그중에서 Dispatchers로 바꿀 수 없는데 바꾸려고 하였다는 에러메시지이다.) : 이를 해결하는 방법이 'flowOn'이다.

fun log2(msg : String) = println("[${Thread.currentThread().name}] $msg")

fun simple2() : Flow<Int> = flow{
    withContext(Dispatchers.Default) {
        for (i in 1..10) {
            delay(100L)
            emit(i)
        }
    }
}

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Default) {
        simple2()
            .collect{
                value -> log("${value}를 받음.")
            }
    }
}