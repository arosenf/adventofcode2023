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
        var result = 0

        val games = lines
            .map(::parseGame)
            .toList()

        result += games.size

        var copies = games
            .flatMap { copies(it, games) }

        while (copies.isNotEmpty()) {
            result += copies.size
            copies = copies.flatMap { copies(it, games) }
        }

        return result
    }

    private fun parseGame(line: String): Game {
        val gameNumber = line
            .substringBefore(':')
            .partition { c -> c.isDigit() }
            .first
            .toInt()
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
        return Game(gameNumber, winningNumbers, actualNumbers)
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

    private fun copies(game: Game, games: List<Game>): List<Game> {
        val hits = countHits(game)
        return if (hits < 1) {
            listOf()
        } else {
            games.subList(game.gameNumber, game.gameNumber + hits)
        }
    }

    data class Game(val gameNumber: Int, val winningNumbers: List<Int>, val actualNumbers: List<Int>)
}
