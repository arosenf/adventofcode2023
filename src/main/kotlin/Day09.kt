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
    fun predict(lines: Sequence<String>): Long {
        val sequences = mutableListOf<List<List<Long>>>()

        // Reduce to all 0
        lines.forEach { l ->
            val sequence = mutableListOf<List<Long>>()
            var current = l.split(' ').map { s -> s.toLong() }

            sequence.add(current)
            while (current.any { it != 0L }) {
                current = current.zipWithNext { x: Long, y: Long -> y - x }
                sequence.add(current)
            }
            sequences.add(sequence)
        }

        // Extrapolate next value
        return sequences.sumOf { s -> s.sumOf { it.last() } }
    }
}
