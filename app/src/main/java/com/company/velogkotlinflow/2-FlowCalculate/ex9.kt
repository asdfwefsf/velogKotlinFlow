package com.company.velogkotlinflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

// 이제 부터는 종단 연산자에 대해서 학습 할 것임 : 종단 연산자란 방출된 Flow의 요소들을 처리함으로서 Flow를 끝내는 연산자이다. : terminal operator : 특정 값 , 컬렉션 등의 결과를 Return한다.
// 종단 연산자는 Flow의 요소를 받아서 1개씩 처리하는 suspend function이다. (Flow 요소를 단 한개씩 처리한다는 것이 아주 중요한 개념인것이야.)
// 종류 : collect , reduce , fold , count , toList , toSet , first , single , collectLatest

// Flow + reduce : Flow 요소를 누적으로 처리한 후 하나의 Flow 요소로 방출한다.

fun main() = runBlocking {
    val value = (1..10)
        .asFlow()
        .reduce { a, b->
            a + b
        }
    println(value)
}