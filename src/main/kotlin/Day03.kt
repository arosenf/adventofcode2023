import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Schematic document not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName)

    val schematic = lines
        .map(String::toList)
        .toList()

    val result =
        if (args[1] == "part1") {
            Day03().readSchematic1(schematic)
        } else {
            Day03().readSchematic2(schematic)
        }

    println("Result: $result")
}

class Day03 {
    fun readSchematic1(schematic: List<List<Char>>): Int {
        val validNumbers = mutableListOf<Int>()
        val validLocations = markValidPartNumberLocations(schematic, ::isValidPartNumberLocationDesignator)

        schematic.forEachIndexed { row, line ->
            var i = 0
            while (i < line.size) {
                if (line[i].isDigit()) {
                    val partNumber = findNumber(line, i, row)
                    if (isAtValidLocation(partNumber, validLocations)) {
                        validNumbers.add(partNumber.number)
                    }
                    i = partNumber.endIndex + 1
                } else {
                    i += 1
                }
            }
        }

        return validNumbers.sum()
    }

    fun readSchematic2(schematic: List<List<Char>>): Int {
        val validGears = mutableListOf<Int>()
        val validLocations = markValidPartNumberLocations(schematic, ::isValidGearLocationDesignator)

        schematic.forEachIndexed { row, line ->
            var i = 0
            while (i < line.size) {
                if (line[i].isDigit()) {
                    val partNumber = findNumber(line, i, row)
                    if (isAtValidLocation(partNumber, validLocations)) {
                        validGears.add(partNumber.number)
                    }
                    i = partNumber.endIndex + 1
                } else {
                    i += 1
                }
            }
        }

        return validGears.sum()
    }

    private fun markValidPartNumberLocations(schematic: List<List<Char>>, validLocationDesignator: (Char) -> Boolean): Set<Position> {
        val validLocations = mutableSetOf<Position>()
        for ((rowIndex, row) in schematic.withIndex()) {
            for ((colIndex, element) in row.withIndex()) {
                if (validLocationDesignator.invoke(element)) {
                    validLocations.add(Position(rowIndex - 1, colIndex - 1))
                    validLocations.add(Position(rowIndex - 1, colIndex))
                    validLocations.add(Position(rowIndex - 1, colIndex + 1))
                    validLocations.add(Position(rowIndex, colIndex - 1))
                    validLocations.add(Position(rowIndex, colIndex + 1))
                    validLocations.add(Position(rowIndex + 1, colIndex - 1))
                    validLocations.add(Position(rowIndex + 1, colIndex))
                    validLocations.add(Position(rowIndex + 1, colIndex + 1))
                }
            }
        }
        return validLocations.toSet()
    }

    private fun isValidPartNumberLocationDesignator(char: Char): Boolean {
        return !char.isDigit() && char != '.'
    }

    private fun isValidGearLocationDesignator(char: Char): Boolean {
        return char == '*'
    }

    // Expand left and right from given position until no digits -> found a number
    // No guards here: expects it to point at a digit
    private fun findNumber(line: List<Char>, pos: Int, row: Int): PartNumber {
        val number = mutableListOf(line[pos])

        // Scan to the right
        var rightScan = pos + 1
        while (rightScan < line.size) {
            val candidate = line[rightScan]
            if (candidate.isDigit()) {
                number.add(candidate)
            } else {
                break
            }
            rightScan += 1
        }

        // Scan to the left
        var leftScan = pos - 1
        while (leftScan >= 0) {
            val candidate = line[leftScan]
            if (candidate.isDigit()) {
                number.add(0, candidate)
            } else {
                break
            }

            leftScan -= 1
        }

        return PartNumber(
            number.joinToString("").toInt(),
            row,
            leftScan + 1,
            rightScan - 1
        )
    }

    private fun isAtValidLocation(partNumber: PartNumber, validLocations: Set<Position>): Boolean {
        return generateSequence(partNumber.startIndex) { it + 1 }
            .take(partNumber.endIndex + 1 - partNumber.startIndex)
            .map { i -> Position(partNumber.row, i) }
            .toList()
            .intersect(validLocations)
            .isNotEmpty()
    }

    data class PartNumber(val number: Int, val row: Int, val startIndex: Int, val endIndex: Int)
    data class Position(val rowIndex: Int, val colIndex: Int)
}
