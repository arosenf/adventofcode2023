import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day08Test {
    private val day08 = Day08()

    @Test
    fun `Parsing the example document 1 for part 1 should return 2`() {
        val network = readLines("day08/network_example1.txt")

        val actual = day08.navigate(network)
        Assertions.assertEquals(2, actual)
    }

    @Test
    fun `Parsing the example document 2 for part 1 should return 6`() {
        val network = readLines("day08/network_example2.txt")

        val actual = day08.navigate(network)
        Assertions.assertEquals(6, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 19631`() {
        val network = readLines("day08/network.txt")

        val actual = day08.navigate(network)
        Assertions.assertEquals(19631, actual)
    }
}
