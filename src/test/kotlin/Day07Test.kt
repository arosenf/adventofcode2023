import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day07Test {
    private val day07 = Day07()

    @Test
    fun `Parsing the example document for part 1 should return 6440`() {
        val hands = readHands(readLines("day07/hands_example.txt"), ::evaluateHandType)

        val actual = day07.evaluateHands(hands, handValues)
        Assertions.assertEquals(6440, actual)
    }

    @Test
    fun `Parsing the document for part 1 should return 249483956`() {
        val hands = readHands(readLines("day07/hands.txt"), ::evaluateHandType)

        val actual = day07.evaluateHands(hands, handValues)
        Assertions.assertEquals(249483956, actual)
    }

    @Test
    fun `Parsing the example document for part 2 should return 5905`() {
        val hands = readHands(readLines("day07/hands_example.txt"), ::evaluateHandTypeWithJoker)

        val actual = day07.evaluateHands(hands, handValuesWithJoker)
        Assertions.assertEquals(5905, actual)
    }

    @Test
    fun `Parsing the document for part 2 should return 252137472`() {
        val hands = readHands(readLines("day07/hands.txt"), ::evaluateHandTypeWithJoker)

        val actual = day07.evaluateHands(hands, handValuesWithJoker)
        Assertions.assertEquals(252137472, actual)
    }
}
