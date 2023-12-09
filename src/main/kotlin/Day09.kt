import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Hands not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName)

    val result =
        if (args[1] == "part1") {
            Day09().predict(lines)
        } else {
            -1
        }

    println("Result: $result")
}

class Day09 {
    fun predict(lines: Sequence<String>): Int {
        return -1
    }
}
