import kotlin.math.max
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Games document not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName)

    val result =
        if (args[1] == "part1") {
            Day02().parseGames(lines, 1)
        } else {
            Day02().parseGames(lines, 2)
        }

    println("Result: $result")
}

class Day02 {
    fun parseGames(games: Sequence<String>, part: Int): Int {
        return games
            .map { if (part == 1) gameValue1(it) else gameValue2(it) }
            .reduce { x, y -> x + y }
            .or(0)
    }

    private fun gameValue1(game: String): Int {
        return if (game.substringAfter(':')
                .splitToSequence(';')
                .map { toRound(it) }
                .filter { !isValidRound(it) }
                .count() > 0
        ) 0 else toGameId(game)
    }

    private fun gameValue2(game: String): Int {
        val minRound = game.substringAfter(':')
            .splitToSequence(';')
            .map { toRound(it) }
            .reduce { r1, r2 ->
                Round(
                    max(r1.red, r2.red),
                    max(r1.green, r2.green),
                    max(r1.blue, r2.blue)
                )
            }

        return minRound.red * minRound.green * minRound.blue
    }

    private fun toGameId(game: String): Int {
        return game.substringBefore(':').filter { c -> c.isDigit() }.toInt()
    }

    private fun toRound(round: String): Round {
        var red = 0
        var green = 0
        var blue = 0

        round.splitToSequence(',').forEach { s ->
            val (digit, color) = s.partition { c -> c.isDigit() }
            when (color.trim()) {
                "red" -> red = digit.toInt()
                "green" -> green = digit.toInt()
                "blue" -> blue = digit.toInt()
            }
        }

        return Round(red, green, blue)
    }

    private fun isValidRound(round: Round): Boolean {
        return round.red <= bag["red"]!!
                && round.green <= bag["green"]!!
                && round.blue <= bag["blue"]!!
    }

    data class Round(val red: Int, val green: Int, val blue: Int)

    private val bag = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )
}
