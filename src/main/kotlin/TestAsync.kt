import kotlinx.coroutines.*

fun main() {
    // Scope handling coroutines for a particular layer of my app
    val scope = CoroutineScope(SupervisorJob())

    scope.launch {
        println("Parent coroutine ${Thread.currentThread().name}")
        val mapped = (1..10).map {
            async {
                println("Child coroutine # $it running in thread ${Thread.currentThread().name}")
            }
        }

        mapped.awaitAll()
        println("End scope!")
    }

    Thread.sleep(500)
}
