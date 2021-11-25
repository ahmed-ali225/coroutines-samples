import kotlinx.coroutines.*

fun main() {
    // Scope handling coroutines for a particular layer of my app
    val scope = CoroutineScope(SupervisorJob())

    scope.launch {
        println("Parent coroutine ${Thread.currentThread().name}")
        val mapped = (1..10).map {
            async {
                try {

                    retryBackoffExponential() {
                        if (it == 5)
                            throw Exception("Thrown Error!!!!")
                        println("Child coroutine # $it running in thread ${Thread.currentThread().name}")
                    }
                } catch (e: Exception){
                    println("Exception Caught!!")
                }
            }
        }

        mapped.awaitAll()
        println("End scope!")
    }

    Thread.sleep(5000)
}



suspend fun <T> retryBackoffExponential(
times: Int = 5,
initialDelay: Long = 100, // 0.1 second
maxDelay: Long = 1000,    // 1 second
factor: Double = 2.0,
block: suspend () -> T): T
{
    var currentDelay = initialDelay
    var counter = times;
    repeat(times - 1) {
        try {
            println("Trying iteration $counter")
            return block()
        } catch (e: Exception) {
            counter--;
            println("An error occurred! remaining retries are $counter")
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    println("An error occurred! last attempt $counter")
    return block() // last attempt
}
