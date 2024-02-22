package com.company.velogkotlinflow.`3-FlowContext`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

// Flow의 Context를 변경하는 방법은 .flowOn을 통해서 변경 할 수 있다.

// Flow의 Context를 변경하는 이유 : Flow의 요소 생성과 처리를 다른 Coroutine Context에서 실행하기 위해서 + 'Flow 방출 스레드'와 'Flow 종단 스레드' 분리
// 예를들면 , 복잡한 데이터 처리작업을 백그라운드 스레드에서 실행하고 싶을 때 , flowOn 연산자를 사용해서
// Flow의 Context를 Dispatchers.Default||Dispatchers.IO 같은 백그라운드 스레드로 변경해서 처리하면,
// 복잡한 데이터를 방출하고 처리하는 작업은 백그라운드에서 실행하고 , 최종적으로 종단 연산자를 통해서 Flow의 요소들을
// 처리하는 방법은 , 기존의 CoroutineContext(ex:MainThread)에서 실행 할 수 있게된다.

// flowOn은 UpStream에 있는 대상을 어떤 Context에서 실행시킬지 결정한다. , DownStream은 종단 연산자가 실행되는 Coroutine의 Context 내부에서 실행된다.
// flowOn의 위치에 따라서 UpStream과 DownStream이 결정이 된다. (상대적인 위치야.)
// flowOn을 통해서 Flow의 연산||emit이 실행될 Context를 변경시킬 수 있다 : 만약, flowOn이 두번 연속으로 사용되었다면, 최상단의 flowOn이 우선 순위이다.

// flowOn는 내부적으로 버퍼링 메커니즘이 존재한다. 이는, Flow의 방출 부분과 수집 부분이 서로 다른 Context에서 동시에 실행 될 수 있기 때문이다.
// 참고로 Flow의 방출과 수집은 모두 Coroutine에서만 실행 될 수 있다 : val simple3 = simple3()는 객체를 선언하는것에 불과하므로 실제로 simple3()가 방출되고 방출된 simple3()를 수집하는 과정은
// Coroutine에서 일어난다.
// flowOn의 버퍼링 메커니즘은 암시적이고 , 명시적으로 버퍼링 메커니즘을 사용하고 싶다면, .buffer()를 사용하면 된다.

//

// -Dkotlinx.coroutines.debug
fun log3(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple3(): Flow<Int> = flow {
    for (i in 1..10) {
        delay(100L)
        log3("값 ${i}를 emit합니다.")
        emit(i)
    } // Dispatchers.IO의 UpStream -> Dispatchers.IO에서 실행된다.
}
.flowOn(Dispatchers.IO) // 위치
.map {
    it * 2 // Dispatchers.Default의 UpStream -> Dispatchers.Default에서 실행된다.
}
.flowOn(Dispatchers.Default) // 위치


fun main() = runBlocking {
    simple3()
        .collect { // DownStream
                value ->
            log("${value}를 받음.")
        }
}
