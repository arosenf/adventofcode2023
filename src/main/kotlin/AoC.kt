import java.util.stream.Stream
import kotlin.system.exitProcess

fun readLines(fileName: String): Stream<String> {
    val resource = {}::class.java.getResourceAsStream(fileName)?.bufferedReader()
    if (resource == null) {
        println("File $fileName not found")
        exitProcess(1)
    }
    return resource.lines()
}
