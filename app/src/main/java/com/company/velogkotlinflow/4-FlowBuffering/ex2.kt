package com.company.velogkotlinflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// Flow + buffer : 송신측이 더 이상 종단 연산자의 실행 완료를 기다리지 않고 , 방출과 종단 연산자가 병렬적으로 실행된다. 주의 : Flow의 요소 방출이 병렬적으로 일어나는 것이 아니야!

// buffer를 사용하지 않았던 지난 코드들에서 Flow는 요소 하나를 방출하고 방출된 요소 하나가 종단 연산자에서 받아서 처리되면,
// 다음 요소를 방출하는 식으로 순차적인 실행 구조를 가졌다.
// 하지만, 이는 방출과 수신이 직렬로 실행되서 실행 속도를 늦추는 구조적인 문제를 지니고 있었다. 따라서 buffer의 필요성이 대두되었다.
// buffer를 사용하게 되면 방출된 요소가 수신 완료 될 때까지 기다리지 않고, 바로 바로 다음 순서의 요소들을 방출하고,
// 수신측 또한 Flow의 각 요소들이 독립적으로 처리가 되어 방출과 수신이 병렬로 처리되기 때뮨에 실행 속도를 증가시킬 수 있게 되었다.

// buffer()는 방추된 요소들을 임시로 저장 할 수 있는 버퍼를 만들어서 , 방출된 요소들을 보관하고 수신측에서 임의의 요소를 받을 수 있는
// 준비가 되면 해당 요소를 넘겨준다.

// 아래코드는 ex1의 코드에 buffer를 추가 시켜서 송신 측이 더 이상 기다리지 않게 만든 코드이다.

fun simple2() : Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    val time=  measureTimeMillis {
        simple2().buffer()
            .collect { value ->
            delay(300)
            println(value)
        }
    }
    println("Collected in ${time} ms")
}