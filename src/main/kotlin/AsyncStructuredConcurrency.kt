import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = doSomethingUsefulOne()
//        val two = doSomethingUsefulTwo()
//        println("The answer is ${one + two}")
//    }
//    println("Completed in $time ms")
//}

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async/*(start = CoroutineStart.LAZY)*/ { doSomethingUsefulOne() }
        val two = async/*(start = CoroutineStart.LAZY)*/ { doSomethingUsefulTwo() }

//        one.start()
//        two.start()

        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

// The result type of somethingUsefulOneAsync is Deferred<Int>
@OptIn(DelicateCoroutinesApi::class)
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// The result type of somethingUsefulTwoAsync is Deferred<Int>
@OptIn(DelicateCoroutinesApi::class)
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}
