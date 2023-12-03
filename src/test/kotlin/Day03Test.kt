import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day03Test {
    private val day03 = Day03()

    @Test
    fun `Parsing the example document for part 1 should return 4361`() {
        val actual = day03.readSchematic1(readLines("day03/schematic_example.txt"))
        Assertions.assertEquals(4361, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 539590`() {
        val actual = day03.readSchematic2(readLines("day03/schematic.txt"))
        Assertions.assertEquals(539590, actual)
    }

    @Test
    fun `Parsing the example document for part 2 should return 467835`() {
        val actual = day03.readSchematic1(readLines("day03/schematic_example.txt"))
        Assertions.assertEquals(467835, actual)
    }

    @Test
    @Disabled
    fun `Parsing the document for part 2 should return -1`() {
        val actual = day03.readSchematic2(readLines("day03/schematic.txt"))
        Assertions.assertEquals(-1, actual)
    }
}
