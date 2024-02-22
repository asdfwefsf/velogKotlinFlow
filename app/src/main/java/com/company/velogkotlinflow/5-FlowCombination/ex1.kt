package com.company.velogkotlinflow.`5-FlowCombination`

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

// 여러개의 Flow간의 결합

// Flow + Zip : 두개의 Flow가 있을 때 양쪽 Flow의 요소가 둘 다 준비가 되었을 때 두 요소를 묶어서 묶어서 새로운 Flow 요소를 만들어 낸다.

fun main() = runBlocking {
    val nums = (1..3).asFlow()
    val strs = flowOf("일", "이", "삼")
    nums.zip(strs) { a, b -> "${a}은(는) $b" }
        .collect {
            println(it)
        }
}