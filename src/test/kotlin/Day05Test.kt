import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day05Test {
    private val day05 = Day05()

    @Test
    fun `Parsing the example document for part 1 should return 35`() {
        val actual = day05.findLowestLocation(readLines("day05/almanac_example.txt"))
        Assertions.assertEquals(35, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 240320250`() {
        val actual = day05.findLowestLocation(readLines("day05/almanac.txt"))
        Assertions.assertEquals(240320250, actual)
    }

    @Test
    @Disabled
    fun `Parsing the example document for part 2 should return -1`() {
        val actual = day05.findLowestLocation(readLines("day05/almanac_example.txt"))
        Assertions.assertEquals(-1, actual)
    }

    @Test
    @Disabled
    fun `Parsing the document for part 2 should return -1`() {
        val actual = day05.findLowestLocation(readLines("day05/almanac.txt"))
        Assertions.assertEquals(-1, actual)
    }
}
