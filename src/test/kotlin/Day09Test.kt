import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day09Test {
    private val day09 = Day09()

    @Test
    fun `Parsing the example document 1 for part 1 should return 114`() {
        val report = readLines("day09/report_history_example.txt")

        val actual = day09.predict(report)
        Assertions.assertEquals(114, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 1939607039`() {
        val report = readLines("day09/report_history.txt")

        val actual = day09.predict(report)
        Assertions.assertEquals(1939607039L, actual)
    }

    @Test
    fun `Parsing the example document for part 2 should return 2`() {
        val network = readLines("day09/report_history_example.txt")

        val actual = day09.predictBackwards(network)
        Assertions.assertEquals(2, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return 1041`() {
        val network = readLines("day09/report_history.txt")

        val actual = day09.predictBackwards(network)
        Assertions.assertEquals(1041, actual)
    }
}
