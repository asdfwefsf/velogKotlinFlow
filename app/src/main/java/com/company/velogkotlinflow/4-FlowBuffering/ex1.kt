package com.company.velogkotlinflow.`4-FlowBuffering`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// 지금까지 Flow를 사용해 보았는데 , 데이터를 만드는 생산 측과 소비단은 같은 속도로 움직일 수 없다.
// 그렇기 때문에 , 버퍼링하는 방법을 배워서 유연하게 동작하도록 만들 수 있다.

// 버퍼가 없는 Flow : 기존에 학습했던 Flow와 동일하다.
// 아래의 코드는 송신측과 수신측 모두 바쁘다는 전제하에서(각각 100ms , 300ms 씩 지연이 존재해.) 만들어진 예시 코드이다.

fun simple() : Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    val time=  measureTimeMillis {
        simple().collect { value ->
            delay(300)
            println(value)
        }
    }
    println("Collected in ${time} ms")
}