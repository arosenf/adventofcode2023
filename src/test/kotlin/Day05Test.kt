import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day05Test {
    private val day05 = Day05()

    @Test
    fun `Parsing the example document for part 1 should return 35`() {
        val actual = day05.findLowestLocation1(readLines("day05/almanac_example.txt"))
        Assertions.assertEquals(35, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 240320250`() {
        val actual = day05.findLowestLocation1(readLines("day05/almanac.txt"))
        Assertions.assertEquals(240320250, actual)
    }

    @Test
    fun `Parsing the example document for part 2 should return 46`() {
        val actual = day05.findLowestLocation2(readLines("day05/almanac_example.txt"))
        Assertions.assertEquals(46, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return 28580589`() {
        val actual = day05.findLowestLocation2(readLines("day05/almanac.txt"))
        Assertions.assertEquals(28580589, actual)
    }
}
