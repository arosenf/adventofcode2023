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

        val debug = sequences.map { sequence ->
            sequence.map { line -> line.first() }
                .reversed()
                .zipWithNext { x, y -> y - x }
        }
        println("$debug")


        // Extrapolate previous value
        return sequences.sumOf { sequence ->
            sequence.map { line -> line.first() }
                .reversed()
                .zipWithNext { x, y -> println("$y - $x = ${y-x}"); y - x }
                .last()
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
