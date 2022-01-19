package coroutines

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


suspend fun main() {

    threadTest()

    println("a")
    println("b")
    println("c")

    coroutineTest()

}

// 서로 다른 두 스레드가 돌아가며 실행되기 때문에 1과 2가 번갈아서 출력된다. 한번 Context Switching이 일어나면 한 스레드가 계속실행되다가 다른 스레드로 넘어가는 걸 볼 수 있다.
fun threadTest() {
    thread {
        println("Thread: ${Thread.currentThread().name}")
        repeat(500) {
            print("t1")
        }
    }

    thread {
        println("Thread: ${Thread.currentThread().name}")
        repeat(500) {
            print("t2")
        }
    }
}

// 같은 스레드 안의 코루틴들은 기본적으로는 스레드처럼 선점을 하려고 하지 않는다. 한 코루틴이 끝나면 그 스레드에 있는 다른 코루틴이 실행된다. 따라서 스레드처럼 병렬적으로(Parallel) 실행되지 않는다. 코루틴은 동시적(Concurrency)인 방식이다.
suspend fun coroutineTest() = runBlocking {
    val job = arrayListOf<Job>()

    job += launch {
        println("Thread: ${Thread.currentThread().name}")
        repeat(500) {
            print("1")
        }
        println()
    }

    job += launch {
        println("Thread: ${Thread.currentThread().name}")
        repeat(500) {
            print("2")
        }
        println()
    }

    // 작업이 끝날 때까지 대기
    println("Wating... (${Thread.currentThread().name})")
    job.forEach {
        it.join()
    }
}
