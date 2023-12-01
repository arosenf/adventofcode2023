import java.util.stream.Stream
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        usage("?")
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

fun usage(doc: String) {
    println("Calibration document $doc not found")
}

class Day01 {
    /*
     * Part 1
     */
    fun parseCalibrationDocumentDigits(calibrationDocument: String): Int {
        return parse(openDocument(calibrationDocument), ::extractCalibrationValueByDigits)
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
        return parse(openDocument(calibrationDocument), ::extractCalibrationValueByDigitsAndText)
    }

    private val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    private fun extractCalibrationValueByDigitsAndText(calibrationLine: String): Int {
        val first = "4"
        var last = "2"



        return "$first$last".toInt()
    }

    /*
     * Stuff
     */
    private fun openDocument(calibrationDocument: String): Stream<String> {
        val resource = {}::class.java.getResourceAsStream(calibrationDocument)?.bufferedReader()
        if (resource == null) {
            usage(calibrationDocument)
            exitProcess(1)
        }
        return resource.lines()
    }

    private fun parse(lines: Stream<String>, extractor: (String) -> Int): Int {
        return lines
            .map(extractor)
            .reduce { x, y -> x + y }
            .orElse(0)
    }
}
