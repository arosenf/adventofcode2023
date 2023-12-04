import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day04Test {
    private val day04 = Day04()

    @Test
    fun `Parsing the example document for part 1 should return 13`() {
        val actual = day04.countPoints(readLines("day04/scratchcard_example.txt"))
        Assertions.assertEquals(13, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 22488`() {
        val actual = day04.countPoints(readLines("day04/scratchcard.txt"))
        Assertions.assertEquals(22488, actual)
    }

    @Test
    @Disabled
    fun `Parsing the example document for part 2 should return -1`() {
//        val actual = day04.countPoints2(readLines("day04/scratchcard_example.txt"))
//        Assertions.assertEquals(-1, actual)
    }

    @Test
    @Disabled
    fun `Parsing the document for part 2 should return -1`() {
//        val actual = day04.countPoints2(readLines("day04/scratchcard.txt"))
//        Assertions.assertEquals(-1, actual)
    }
}
