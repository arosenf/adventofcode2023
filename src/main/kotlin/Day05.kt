import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Almanac not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName)

    val result =
        if (args[1] == "part1") {
            Day05().findLowestLocation(lines)
        } else {
            -1
//            Day05().foo(lines)
        }

    println("Result: $result")
}

class Day05 {
    fun findLowestLocation(lines: Sequence<String>): Int {
        return -1
    }
}
