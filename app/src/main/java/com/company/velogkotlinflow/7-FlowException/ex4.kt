package com.company.velogkotlinflow.`7-FlowException`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

// Flow의 catch 투명성
// Flow의 catch 투명성이란, "catch 연산자는 UpStream(catch 연산자를 사용하기 전의 코드)에만 영향을 미치고 DownStream(catch 연산자를 사용하고 난 후의 코드)에는 영향을 미치지 않는다."는 것이다.



fun simple4(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    simple4() // catch가 영향을 미칠 수 있어.
        .catch { e -> print("Caught $e") } // UpStream에 영향(o) DownStream에 영향(x)
        // 여기 아래로는 catch가 영향을 미치지 않는다.
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }

}