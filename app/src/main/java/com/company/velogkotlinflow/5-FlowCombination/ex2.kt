package com.company.velogkotlinflow.`5-FlowCombination`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

// Flow + combine : 두개의 Flow가 있을 때 양쪽 Flow 중 하나의 Flow에서 새로운 요소가 방출 될때마다 ,
// 다른쪽의 Flow의 최신 요소와 결합하여 새로운 요소를 만들어서 방출한다. 만약, 반대쪽의 Flow가 아직 새로운 요소를 방출하지 않았으면
// 가장 최근에 방출된 요소를 반대쪽의 Flow의 최신 요소로 인식한다.

// Flow의 요소가 짝을 이룰 필요 없이 최신의 요소를 이용해 가공해야 하는 경우에 사용 할 수 있다.

fun main() = runBlocking<Unit> {// 1 일 // 2 일 // 3 일 // 3 이 // 3 삼
    val nums2 = (1..3).asFlow().onEach { delay(100L) }
    val strs2 = flowOf("일" , "이" , "삼").onEach { delay(200L) }
    nums2.combine(strs2) { a , b -> "${a}은(는) $b"}
        .collect {value ->
            println(value)
        }
}

// (100) 1 (200) 2 (300) 3
// (200) 일 (400) 이 (600) 삼
