import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day06Test {
    private val day06 = Day06()

    @Test
    fun `Parsing the example document for part 1 should return 288`() {
        val lines = readLines("day06/races_example.txt").toList()
        val times = splitDay06Part1(lines[0])
        val distances = splitDay06Part1(lines[1])

        val actual = day06.findMarginOfError(times, distances)
        Assertions.assertEquals(288, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 252000`() {
        val lines = readLines("day06/races.txt").toList()

        val times = splitDay06Part1(lines[0])
        val distances = splitDay06Part1(lines[1])

        val actual = day06.findMarginOfError(times, distances)
        Assertions.assertEquals(252000, actual)
    }

    @Test
    fun `Parsing the example document for part 2 should return 71503`() {
        val lines = readLines("day06/races_example.txt").toList()
        val times = splitDay06Part2(lines[0])
        val distances = splitDay06Part2(lines[1])

        val actual = day06.findMarginOfError(times, distances)
        Assertions.assertEquals(71503, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return 36992486`() {
        val lines = readLines("day06/races.txt").toList()

        val times = splitDay06Part2(lines[0])
        val distances = splitDay06Part2(lines[1])

        val actual = day06.findMarginOfError(times, distances)
        Assertions.assertEquals(36992486, actual)
    }
}
