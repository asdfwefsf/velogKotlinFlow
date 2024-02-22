package com.company.velogkotlinflow.`2-FlowCalculate`

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.runBlocking

// Flow + filterNot : Flow + filter{}의 정반대 개념으로서 {}에 들어간 술어와 일치하지 않는 Flow의 요소들만 새로운 Flow로 반환한다.

// 술어(predicate)를 수정해서 새로운 Flow를 반환 할 수 있지만 , 정반대 개념이라면 간편하게 filterNot{}을 통해서 새 Flow를 반환 할 수 있다.
// public inline fun <T> Flow<T>.filterNot(crossinline predicate: suspend (T) -> Boolean): Flow<T> = transform { value ->
//     if (!predicate(value)) return@transform emit(value)
// }
fun main() = runBlocking {
    (1..20).asFlow().filterNot {
        (it % 2) == 0
    }.collect {
        println(it)
    }
}