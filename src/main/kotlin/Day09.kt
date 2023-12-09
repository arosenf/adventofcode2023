import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Input not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName)

    val result =
        if (args[1] == "part1") {
            Day09().predict(lines)
        } else {
            Day09().predictBackwards(lines)
        }

    println("Result: $result")
}

class Day09 {
    fun predict(lines: Sequence<String>): Long {
        val sequences = reduceErrors(lines)

        // Extrapolate next value
        return sequences.sumOf { sequence -> sequence.sumOf { it.last() } }
    }

    fun predictBackwards(lines: Sequence<String>): Long {
        val sequences = reduceErrors(lines)

        return sequences.sumOf { sequence ->
            val pairs = sequence.map { line -> Pair(0L, line.first()) }
                .reversed()
                .toMutableList()

            // Cannot modify list in-place with zipWithNext
            for (i in 0..<pairs.size - 1) {
                val current = pairs[i]
                val next = pairs[i + 1]
                pairs[i + 1] = Pair(next.second - current.first, next.second)
            }

            pairs.last().first
        }
    }
}

private fun reduceErrors(lines: Sequence<String>): List<List<List<Long>>> {
    val sequences = mutableListOf<List<List<Long>>>()

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
    return sequences
}
