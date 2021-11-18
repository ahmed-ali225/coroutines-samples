import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

fun main() {
    val semaphore = Semaphore(5)

    val scope = CoroutineScope(SupervisorJob())

    scope.launch {
        println("Parent coroutine ${Thread.currentThread().name}")
        val mapped = (1..10000).map {
            async {
                semaphore.withPermit {
                    println("Child coroutine # $it running in thread ${Thread.currentThread().name}")
                }
            }
        }

        mapped.awaitAll()
        println("End scope!")
    }

    Thread.sleep(1000)
}
