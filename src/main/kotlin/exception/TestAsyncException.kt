import kotlinx.coroutines.*

//fun main() = runBlocking {
//    val rootScope = CoroutineScope(Job())
//    val deferredResult = rootScope.async {
//        throw Exception()
//    }
//
////   deferredResult.await()
//
//    Thread.sleep(200)
//}

//fun main() {
//
//    // Scope handling coroutines for a particular layer of my app
//    val scope = CoroutineScope(SupervisorJob())
//
//    scope.launch {
//        println("Parent coroutine ${Thread.currentThread().name}")
//        val mapped = (1..10).map {
//            async {
//                println("Child coroutine # $it running in thread ${Thread.currentThread().name}")
//                try {
//
//                    if (it != 5)
//                        println("processing iteration $it")
//                    else
//                        throw RuntimeException("Error in async!")
//                } catch (t: Throwable) {
//                    println("Error caught!")
//                }
//            }
//        }
//
////        try {
//            mapped.awaitAll()
////        } catch (t: Throwable) {
////            println("Error caught!")
////        }
//        println("End scope!")
//    }
//
//    Thread.sleep(500)
//
//}


fun main() {
    val scope = CoroutineScope(Job())

    scope.launch {
        println("Start!")
        println("current thread ${Thread.currentThread().name}")
            launch {
                kotlin.runCatching {

                    println("Child coroutine start!")
                    println("child thread ${Thread.currentThread().name}")


                    throw Exception()

                    println("Child coroutine end!")
                }
            }
        delay(200)
        println("Done!")
    }

    Thread.sleep(500)
}
