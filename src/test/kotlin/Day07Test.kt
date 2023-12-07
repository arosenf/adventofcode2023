import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day07Test {
    private val day07 = Day07()

    @Test
    fun `Parsing the example document for part 1 should return 6440`() {
        val hands = readHands(readLines("day07/hands_example.txt"))

        val actual = day07.evaluateHands(hands)
        Assertions.assertEquals(6440, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 249483956`() {
        val hands = readHands(readLines("day07/hands.txt"))

        val actual = day07.evaluateHands(hands)
        Assertions.assertEquals(249483956, actual)
    }
/*
    @Test
    fun `Parsing the example document for part 2 should return 5905`() {
        val hands = readHands(readLines("day07/hands_example.txt"))

        val actual = day07.evaluateHands(hands)
        Assertions.assertEquals(5905, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return -1`() {
        val hands = readHands(readLines("day07/hands.txt"))

        val actual = day07.evaluateHands(hands)
        Assertions.assertEquals(-1, actual)
    }
*/
}
