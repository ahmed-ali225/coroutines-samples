import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.*

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        coroutineScope { // scope for coroutines
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}

var counter = 0  // problem
//val counter = AtomicInteger() // solution 1

//val counterContext = newSingleThreadContext("CounterContext") // solution 2

val mutex = Mutex() // solution 3

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
//            withContext(counterContext) { // solution 2 start
            mutex.withLock { // solution 3 start
                counter++ // problem
            } // solution 3 end
//            } // solution 2 end
//            counter.incrementAndGet() // solution 1
        }
    }
    println("Counter = $counter")
}
