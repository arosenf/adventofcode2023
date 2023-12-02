import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day01Test {
    private val day01 = Day01()

    @Test
    fun `Parsing the example document for part 1 should return 142`() {
        val actual = day01.parseCalibrationDocumentDigits("day01/calibration_document_example_1.txt")
        Assertions.assertEquals(142, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 54304`() {
        val actual = day01.parseCalibrationDocumentDigits("day01/calibration_document.txt")
        Assertions.assertEquals(54304, actual)
    }

    @Test
    fun `Parsing the example document for part 2 should return 281`() {
        val actual = day01.parseCalibrationDocumentDigitsAndText("day01/calibration_document_example_2.txt")
        Assertions.assertEquals(281, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return 54418`() {
        val actual = day01.parseCalibrationDocumentDigitsAndText("day01/calibration_document.txt")
        Assertions.assertEquals(54418, actual)
    }
}
