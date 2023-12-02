import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day02Test {
    private val day02 = Day02()

    @Test
    fun `Parsing the example document for part 1 should return 8`() {
        val actual = day02.parseGames(readLines("day02/games_example.txt"), 1)
        Assertions.assertEquals(8, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 2449`() {
        val actual = day02.parseGames(readLines("day02/games.txt"), 1)
        Assertions.assertEquals(2449, actual)
    }

    @Test
    fun `Parsing the example document for part 2 should return 2286`() {
        val actual = day02.parseGames(readLines("day02/games_example.txt"), 2)
        Assertions.assertEquals(2286, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return 63981`() {
        val actual = day02.parseGames(readLines("day02/games.txt"), 2)
        Assertions.assertEquals(63981, actual)
    }
}
