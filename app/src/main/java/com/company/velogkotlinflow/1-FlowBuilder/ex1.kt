package com.company.velogkotlinflow.`1-FlowBuilder`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

// FlowBuilder : flow {  } 별도로 emit을 해줘야 한다.
// flowOf , asFlow는 모두 함수 내부에서 flow{}를 사용하고 있고 , flow{}는 emit()을 가지고 있어서 flowOf , asFlow는 별도로 emit()을 진행 할 필요가 없다.

// flowOf , asFlow는 모두 함수 내부에서 flow{}를 사용하고 있고 , flow의 후행 람다식 코드 블락은 suspendScope이기 때문에 suspend function을 담을 수 있다. [아래 코드 참고]
// public fun <T> flow(@BuilderInference block: suspend FlowCollector<T>.() -> Unit): Flow<T> = SafeFlow(block)

// 위 코드에서 보이는 것처럼 Flow를 정의하는 것은 반드시 Coroutine Scope 내부에서만 정의해야 되는 것은 아니지만 , 'block parameter'가 'suspend 람다'이기 때문에,
// 실제로 코드상에서 [collect를 통해서 Flow를 수집하는 것은] 오직 Coroutine Scope 내부에서만 사용이 가능하다.
// 단, flow 자체는 일반함수이기 때문에 flow{}를 사용해서 함수를 정의하는 것은 가능하다.
// ( + emit()이 suspend function이더라도 Flow는 Cold Stream이기 때문에 실제로는 종단 연산자가 호출되기 전에는 emit()이 실행되지 않아서 일반함수 취급을 받는것이고
// 실제로 Flow()가 emit()이 실행되는 곳은 종단 연산자가 호출될 때 Coroutine Context 내부에서 실행된다.)

fun flowSomething() : Flow<Int> = flow {// Flow<Flow가 방출(emit)할 값의 타입> , 방출이란 스트림의 값을 A로 보내는 흘려보내는 것을 말한다.
    repeat(10) {
        emit(Random.nextInt(0 , 500))
        delay(10L)
    }
}
fun main() = runBlocking {
    // Flow의 요소들을 사용하기 위해서는 종단 연산자를 사용해서 Flow의 요소들을 사용 할 수 있다.
    // Flow는 콜드스트림이기 때문에 요청단에서 종단연산자를 호출해야 값을 방출한다.
    // 콜드스트림 : 용청이 있는 경우에 1:1로 값을 전달한다.
    flowSomething()
        .collect {value ->
        println(value)
    }
}


