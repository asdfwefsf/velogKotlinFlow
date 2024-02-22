package com.company.velogkotlinflow.`2-FlowCalculate`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

// 중간 연산자 : 다른 중간 연산자들과 함께 쓰고 마지막에는 collect()를 통해서 Flow 요소들을 이용 할 수 있다. : 즉, 중간 연산자는 Return 값이 없어.



// Flow 연산자를 연속해서 한 번에 두개 이상의 연산자를 사용 할 수 있다.

// Flow + Map : Flow가 방출한 데이터 스트림을 람다식에서 각각의 개별 요소에 대한 처리를 한 후 새로운 Flow의 요소로 변환 시킨다. : Flow 요소를 하나 가져와서 하나씩 가공해
// <-> reduce : reduce의 코드블락에 필요한 갯수만큼 Flow 요소들을 가져와서 결과값을 넣어주고 누진적으로 계산해준다. : Flow 요소를 누적으로 처리한 후 하나의 Flow 요소로 방출한다.

// 이때, 이런 변환 작업은 순차적으로 수행이 되고 , 각각의 개별 요소에 대해서 독립적으로 발생한다.
// public inline fun <T, R> Flow<T>.map(crossinline transform: suspend (value: T) -> R): Flow<R> = transform { value ->
//    return@transform emit(transform(value))
// } (새로운 Flow 요소를 반환하는 코드 : return@transform emit(transform(value))

fun flowSomething() : Flow<Int> = flow {
    repeat(10) {
        emit(Random.nextInt(0 , 500))
        delay(10L)
    }
}

fun main() = runBlocking {
    flowSomething().map {
        "$it $it"
    }.collect { value ->
        println(value)
    }
}



