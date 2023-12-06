import kotlin.math.min
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
            val times = splitDay06(lines.toList()[0])
            val distances = splitDay06(lines.toList()[1])

            Day06().findMarginOfError(times, distances)
        } else {
            -1
//            Day06().findMarginOfError2(lines)
        }

    println("Result: $result")
}

private val delimiterDay06 = "\\s+".toRegex()
fun splitDay06(line: String): List<Int> {
    return line.substringAfter(':')
        .trim()
        .split(delimiterDay06)
        .map(String::toInt)
}

class Day06 {

    fun findMarginOfError(times: List<Int>, distances: List<Int>): Int {
        return -1
    }
}