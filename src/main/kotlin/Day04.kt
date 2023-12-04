import java.math.BigDecimal
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Scratchcard not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName)

    val result =
        if (args[1] == "part1") {
            Day04().countPoints(lines)
        } else {
            Day04().countPoints2(lines)
        }

    println("Result: $result")
}

class Day04 {
    private val delimiter = "\\s+".toRegex()

    fun countPoints(lines: Sequence<String>): Int {
        return lines
            .map(::parseGame)
            .map(::countHits)
            .map(::score)
            .sum()
    }

    fun countPoints2(lines: Sequence<String>): Int {
        return -1
    }

    private fun parseGame(line: String): Game {
        val winningNumbers = line.substringAfter(':')
            .trim()
            .substringBefore('|')
            .trim()
            .splitToSequence(delimiter)
            .map(String::toInt)
            .toList()
        val actualNumbers = line.substringAfter('|')
            .trim()
            .splitToSequence(delimiter)
            .map(String::toInt)
            .toList()
        return Game(winningNumbers, actualNumbers)
    }

    private fun countHits(game: Game): Int {
        return game.winningNumbers.intersect(game.actualNumbers).size
    }

    private fun score(hitCount: Int): Int {
        return if (hitCount < 1) {
            0
        } else {
            BigDecimal.valueOf(2).pow(hitCount - 1).toInt()
        }
    }

    data class Game(val winningNumbers: List<Int>, val actualNumbers: List<Int>)
}
