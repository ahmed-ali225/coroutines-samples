import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    val job = launch(handler) {
//        try {
//            runCatching {
                networkOperation()
//            }
//        } catch (t: Throwable ) {
//            if (t is CancellationException)
//                throw t
//            println("Caught $t")
//        }
        println("Coroutine still running!")
    }

    delay(500)
    job.cancel()
}

private suspend fun networkOperation() {
    println("Starting network operation....")
    delay(1000)
}
