import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("HAnds not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val hands = readHands(readLines(fileName))

    val result =
        if (args[1] == "part1") {
            Day07().evaluateHands(hands)
        } else {
            -1
        }

    println("Result: $result")
}

fun readHands(lines: Sequence<String>): Hands {
    val hands: List<List<Char>> = arrayListOf()
    val bids: List<Int> = arrayListOf()

    lines.forEach {
        val line = it.split(' ')
        hands.addLast(line.first().toList())
        bids.addLast(line.last().toInt())
    }

    return Hands(
        hands
            .map { Hand(it, HandType.HIGH_CARD) }
            .map(::evaluateHandType)
            .toList(),
        bids
    )
}

fun evaluateHandType(hand: Hand): Hand {
    val groups = hand.hand.groupBy { it }
    val pairsCount = groups.filter { it.value.size == 2 }.size
    val triplesCount = groups.filter { it.value.size == 3 }.size
    val foursCount = groups.filter { it.value.size == 4 }.size
    val fivesCount = groups.filter { it.value.size == 5 }.size

    val handType = when {
        fivesCount == 1 -> HandType.FIVE_OF_A_KIND
        foursCount == 1 -> HandType.FOUR_OF_A_KIND
        triplesCount == 1 && pairsCount == 1 -> HandType.FULL_HOUSE
        triplesCount == 1 -> HandType.THREE_OF_A_KIND
        pairsCount == 2 -> HandType.TWO_PAIR
        pairsCount == 1 -> HandType.ONE_PAIR
        else -> HandType.HIGH_CARD
    }

    return Hand(hand.hand, handType)
}

class Day07 {
    fun evaluateHands(hands: Hands): Int {
        return hands.hands
            .sortedWith(compareBy(
                { it.type.value },
                // Ok, that's a bit cheap 🤷🏽‍♀️
                { handValues[it.hand[0]] },
                { handValues[it.hand[1]] },
                { handValues[it.hand[2]] },
                { handValues[it.hand[3]] },
                { handValues[it.hand[4]] }
            ))
            .mapIndexed { i, hand -> (i + 1) * hands.bids[hands.hands.indexOf(hand)] }
            .sum()
    }
}

data class Hands(val hands: List<Hand>, val bids: List<Int>)
data class Hand(val hand: List<Char>, val type: HandType)

val handValues = mapOf(
    'A' to 14,
    'K' to 13,
    'Q' to 12,
    'J' to 11,
    'T' to 10,
    '9' to 9,
    '8' to 8,
    '7' to 7,
    '6' to 6,
    '5' to 5,
    '4' to 4,
    '3' to 3,
    '2' to 2
)

enum class HandType(val value: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1)
}
