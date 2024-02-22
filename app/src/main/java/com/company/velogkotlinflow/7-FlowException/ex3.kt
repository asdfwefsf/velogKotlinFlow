package com.company.velogkotlinflow.`7-FlowException`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

// Flow의 Exception의 투명성 : Flow 빌더 코드 블록 내에서 예외를 처리하는 것은 예외 투명성을 어기는 것이다.
// 왜냐하면, Flow 내부에서 예외를 처리해버리면 , Flow 빌더 코드 블록 밖에서는 예외가 발생 하였는지 알 수 없기 때문이다.
// 따러서 Flow에서는 catch 연산자를 사용하여 예외를 처리하는 것을 권한다.

// catch 블록에서 [어떤 예외가 발생 하였는지 받을 수 있고, 그 예외를 처리하는 것이다.]
// catch 블록에서 [예외를 새로운 데이터로 만들어 emit을 하거나] , [다시 예외를 던지거나] , [로그를 남길 수 있다.]

fun simple3(): Flow<String> =
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i)
        }
    }
        .map {
            check(it <= 1) { "Crashed on $it" }
            "string $it"
        }

fun main() = runBlocking<Unit> {
    simple3()
        .catch { e -> emit("Caught $e") }
        .collect { value -> println(value) }
}