package com.company.velogkotlinflow.`3-FlowContext`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Flow가 어떠한 Context에서 수행되는 지를 학습할 예정임

// Flow는 기본적으로 현재 Contourine의 Context에서 호출된다.

fun log(msg : String) = println("[${Thread.currentThread().name}] $msg")

fun simple() : Flow<Int> = flow{
    log("flow를 시작합니다.")
    for (i in 1..10) {
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO) {
        simple().collect { log("${it}를 받음.") }
    }
}