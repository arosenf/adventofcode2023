import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day09Test {
    private val day09 = Day09()

    @Test
    fun `Parsing the example document 1 for part 1 should return 2`() {
        val report = readLines("day09/report_history_example.txt")

        val actual = day09.predict(report)
        Assertions.assertEquals(2, actual)
    }

    @Test
    @Disabled
    fun `Parsing the document for part 1 should return -1`() {
        val report = readLines("day09/report_history_example.txt")

        val actual = day09.predict(report)
        Assertions.assertEquals(-1, actual)
    }
/*
    @Test
    fun `Parsing the example document for part 2 should return 6`() {
        val network = readLines("day08/network_example3.txt")

        val actual = day08.navigateGhost(network)
        Assertions.assertEquals(6, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return -1`() {
        val network = readLines("day08/network.txt")

        val actual = day08.navigateGhost(network)
        Assertions.assertEquals(-1, actual)
    }
*/
}
