import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

fun main() {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    val scope = CoroutineScope(Job() + handler)

    scope.launch/*(handler)*/ {
        println("Parent coroutine ${Thread.currentThread().name}")
        launch {
            println("Child coroutine ${Thread.currentThread().name}")
            throw Exception("Error in Launch!")
        }
    }

    Thread.sleep(200)
}
