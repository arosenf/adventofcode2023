import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day02Test {
    private val day02 = Day02()

    @Test
    fun `Parsing the example document for part 1 should return 8`() {
        val actual = day02.parseGames(readLines("day02/games_example_1.txt"))
        Assertions.assertEquals(8, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 2449`() {
        val actual = day02.parseGames(readLines("day02/games.txt"))
        Assertions.assertEquals(2449, actual)
    }

    @Test
    @Disabled
    fun `Parsing the example document for part 2 should return 281`() {
        val actual = day02.parseGames(readLines("day02/games_example_2.txt"))
        Assertions.assertEquals(281, actual)
    }

    @Test
    @Disabled
    fun `Parsing the document for part 2 should return 54418`() {
        val actual = day02.parseGames(readLines("day02/games.txt"))
        Assertions.assertEquals(54418, actual)
    }
}
