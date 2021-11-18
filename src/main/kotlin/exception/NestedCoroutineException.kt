import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main() {
    val scope = CoroutineScope(Job())

    scope.launch {
        println("Parent coroutine ${Thread.currentThread().name}")
//        try {
            launch {
                println("Child coroutine ${Thread.currentThread().name}")
                throw Exception("Error in Launch!")
            }
//        } catch (t: Throwable) {
//            println("${t.message}")
//        }
    }

    Thread.sleep(200)
}
