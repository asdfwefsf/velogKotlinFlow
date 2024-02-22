package com.company.velogkotlinflow.`2-FlowCalculate`

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.runBlocking

// Flow + filter : filter는 조건에 맞는 요소만 남기게 된다. + filter 뒤에 들어가는 조건을 '술어'라고도 부르기도 하고 , predicate라고 부르기도 한다.

// 해당 조건은 람다식에서 나타나며 , 이 람다식에 있는 조건에 일치하는 요소들만 새로운 Flow로 반환한다.
// public inline fun <T> Flow<T>.filter(crossinline predicate: suspend (T) -> Boolean): Flow<T> = transform { value ->
//    if (predicate(value)) return@transform emit(value)
//}

fun main() = runBlocking {
    (1..20).asFlow().filter {
        (it % 2) == 0
    }.collect {
        println(it)
    }
}