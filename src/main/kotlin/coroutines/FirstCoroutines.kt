package coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


suspend fun main() = run {
    first()

    delay(1000L)

    second()

    delay(1000L)

    third()

    delay(1000L)

    fourth()

    delay(1000L)

    refeatFun()
}

fun first() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}

fun second() = runBlocking {
    doWorld1()
}

fun third() = runBlocking {
    doWorld2()
}

suspend fun doWorld1() = coroutineScope {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}

suspend fun doWorld2() = coroutineScope {
    launch {
        delay(2000L)
        println("World 2")
    }

    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}

fun fourth() = runBlocking {
    launch {
        val job = launch {
            delay(1000L)
            print("World!")
        }
        print("Hello")
        job.join()
    }
}

fun refeatFun() = runBlocking {
    repeat(5) {
        delay(1000L)
        print(".")
    }
}
