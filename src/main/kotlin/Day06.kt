import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Almanac not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName).toList()

    val result =
        if (args[1] == "part1") {
            val times = splitDay06Part1(lines[0])
            val distances = splitDay06Part1(lines[1])

            Day06().findMarginOfError(times, distances)
        } else {
            val times = splitDay06Part2(lines[0])
            val distances = splitDay06Part2(lines[1])

            Day06().findMarginOfError(times, distances)
        }

    println("Result: $result")
}

private val delimiterDay06 = "\\s+".toRegex()
fun splitDay06Part1(line: String): List<Long> {
    return line.substringAfter(':')
        .trim()
        .split(delimiterDay06)
        .map(String::toLong)
}

fun splitDay06Part2(line: String): List<Long> {
    return listOf(
        line.substringAfter(':')
            .replace(delimiterDay06, "")
            .toLong()
    )
}

class Day06 {
    fun findMarginOfError(times: List<Long>, distances: List<Long>): Int {
        return times.zip(distances)
            .map { (time, distance) -> (distancesForMs(time) to distance) }
            .map { (times, distance) -> times.filter { time -> time > distance } }
            .map { recordTimes -> recordTimes.count() }
            .reduce { x, y -> x * y }
    }

    private fun distancesForMs(ms: Long): Sequence<Long> {
        return generateSequence(1) { if (it < ms) it + 1 else null }
            .map { (ms - it) * it }
    }
}
