import java.util.stream.Stream
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Calibration document not specified")
        exitProcess(1)
    }
    val calibrationDocument = args.first()
    println("Recovering $calibrationDocument")

    val calibrationValue =
        if (args[1] == "digits") {
            Day01().parseCalibrationDocumentDigits(calibrationDocument)
        } else {
            Day01().parseCalibrationDocumentDigitsAndText(calibrationDocument)
        }

    println("Calibration value: $calibrationValue")
}

class Day01 {
    /*
     * Part 1
     */
    fun parseCalibrationDocumentDigits(calibrationDocument: String): Int {
        return parse(readLines(calibrationDocument), ::extractCalibrationValueByDigits)
    }

    private fun extractCalibrationValueByDigits(calibrationLine: String): Int {
        val (digit, _) = calibrationLine.partition { c -> c.isDigit() }

        return if (digit.isEmpty()) {
            0
        } else {
            "${digit.first()}${digit.last()}".toInt()
        }
    }

    /*
     * Part 2
     */
    fun parseCalibrationDocumentDigitsAndText(calibrationDocument: String): Int {
        return parse(readLines(calibrationDocument), ::extractCalibrationValueByDigitsAndText)
    }

    private fun extractCalibrationValueByDigitsAndText(calibrationLine: String): Int {
        var result = Digits(null, null)
        val pointer = generateSequence(0) { it + 1 }

        pointer.take(calibrationLine.length).forEach { i ->
            result = if (calibrationLine.elementAt(i).isDigit()) {
                foundDigit(result, calibrationLine.elementAt(i))
            } else {
                findWords(result, calibrationLine.substring(i))
            }
        }

        return "${result.first ?: 0}${result.last ?: 0}".toInt()
    }

    private val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    private fun findWords(result: Digits, substring: String): Digits {
        words.forEach { w ->
            if (substring.startsWith(w, true)) {
                return foundWord(result, w)
            }
        }
        return result
    }

    private fun foundDigit(digits: Digits, digit: Char): Digits {
        val value = digit.digitToInt()
        return Digits(
            digits.first ?: value,
            value
        )
    }

    private fun foundWord(digits: Digits, word: String): Digits {
        val value = words.indexOf(word) + 1
        return Digits(
            digits.first ?: value,
            value
        )
    }

    /*
     * Stuff
     */
    data class Digits(val first: Int?, val last: Int?)

    private fun parse(lines: Stream<String>, extractor: (String) -> Int): Int {
        return lines
            .map(extractor)
            .reduce { x, y -> x + y }
            .orElse(0)
    }
}
