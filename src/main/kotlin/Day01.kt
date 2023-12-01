import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        usage("?")
        exitProcess(1)
    }

    val calibrationDocument = args.first()
    println("Recovering $calibrationDocument")

    val calibrationValue = parseCalibrationDocument(calibrationDocument)
    println("Calibration value: $calibrationValue")
}

fun parseCalibrationDocument(calibrationDocument: String): Int {
    val resource = {}::class.java.getResourceAsStream(calibrationDocument)?.bufferedReader()
    if (resource == null) {
        usage(calibrationDocument)
        exitProcess(1)
    }

    return resource.lines()
        .map(::extractCalibrationValue)
        .reduce { x, y -> x + y }
        .orElse(0)
}

fun extractCalibrationValue(calibrationLine: String): Int {
    val (digit, _) = calibrationLine.partition { c -> c.isDigit() }
    return "${digit.first()}${digit.last()}".toInt()
}

fun usage(doc: String) {
    println("Calibration document $doc not found")
}
