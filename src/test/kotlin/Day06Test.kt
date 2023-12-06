import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day06Test {
    private val day06 = Day06()

    @Test
    fun `Parsing the example document for part 1 should return 288`() {
        val lines = readLines("day06/races_example.txt").toList()
        val times = splitDay06(lines[0])
        val distances = splitDay06(lines[1])

        val actual = day06.findMarginOfError(times, distances)
        Assertions.assertEquals(288, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 252000`() {
        val lines = readLines("day06/races.txt").toList()

        val times = splitDay06(lines[0])
        val distances = splitDay06(lines[1])

        val actual = day06.findMarginOfError(times, distances)
        Assertions.assertEquals(252000, actual)
    }
/*
    @Test
    fun `Parsing the example document for part 2 should return -1`() {
        val actual = day06.findLowestLocation2(readLines("day06/races_example.txt"))
        Assertions.assertEquals(-1, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return -1`() {
        val actual = day06.findLowestLocation2(readLines("day06/races.txt"))
        Assertions.assertEquals(-1, actual)
    }
*/
}
