import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Hands not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")

    val result =
        if (args[1] == "part1") {
            val hands = readHands(readLines(fileName), ::evaluateHandType)
            Day07().evaluateHands(hands, handValues)
        } else {
            val hands = readHands(readLines(fileName), ::evaluateHandTypeWithJoker)
            Day07().evaluateHands(hands, handValuesWithJoker)
        }

    println("Result: $result")
}

fun readHands(lines: Sequence<String>, handEvaluator: (Hand) -> Hand): Hands {
    val hands: ArrayList<List<Char>> = arrayListOf()
    val bids: ArrayList<Int> = arrayListOf()

    lines.forEach {
        val line = it.split(' ')
        hands.addLast(line.first().toList())
        bids.addLast(line.last().toInt())
    }

    return Hands(
        hands
            .map { Hand(it, HandType.HIGH_CARD) }
            .map(handEvaluator)
            .toList(),
        bids
    )
}

fun evaluateHandType(hand: Hand): Hand {
    val handTuples = countTuples(hand.hand)

    val handType = when {
        handTuples.fivesCount == 1 -> HandType.FIVE_OF_A_KIND
        handTuples.foursCount == 1 -> HandType.FOUR_OF_A_KIND
        handTuples.triplesCount == 1 && handTuples.pairsCount == 1 -> HandType.FULL_HOUSE
        handTuples.triplesCount == 1 -> HandType.THREE_OF_A_KIND
        handTuples.pairsCount == 2 -> HandType.TWO_PAIR
        handTuples.pairsCount == 1 -> HandType.ONE_PAIR
        else -> HandType.HIGH_CARD
    }

    return Hand(hand.hand, handType)
}

/**
 * four of a kind with joker
 * ===
 * aaaaJ -> becomes a five of a kind
 * aJJJJ -> becomes a five of a kind
 *
 * full house with joker
 * ===
 * aaaJJ -> becomes five of a kind
 * aaJJJ -> becomes five of a kind
 *
 * three of a kind with joker
 * ===
 * aaabJ -> becomes four of a kind
 * abJJJ -> becomes four of a kind
 *
 * two pairs with joker
 * ===
 * aabbJ -> becomes full house
 * aabJJ -> becomes four of a kind
 *
 * one pair with joker
 * ===
 * xxyzJ -> becomes three of a kind
 * xyzJJ -> becomes three of a kind
 *
 * high card with joker
 * ===
 * wxyzJ -> high card becomes pair
 */
fun evaluateHandTypeWithJoker(hand: Hand): Hand {
    return if (!hand.hand.contains('J')) {
        return evaluateHandType(hand)
    } else {
        val handTuples = countTuples(hand.hand)

        val handType = when {
            handTuples.fivesCount == 1 -> HandType.FIVE_OF_A_KIND
            handTuples.foursCount == 1 -> HandType.FIVE_OF_A_KIND
            handTuples.triplesCount == 1 && handTuples.pairsCount == 1 -> HandType.FIVE_OF_A_KIND
            handTuples.triplesCount == 1 -> HandType.FOUR_OF_A_KIND
            handTuples.pairsCount == 2 && handTuples.jokersCount == 1 -> HandType.FULL_HOUSE
            handTuples.pairsCount == 2 && handTuples.jokersCount == 2 -> HandType.FOUR_OF_A_KIND
            handTuples.pairsCount == 1 -> HandType.THREE_OF_A_KIND
            else -> HandType.ONE_PAIR
        }

        Hand(hand.hand, handType)
    }
}

fun countTuples(hand: List<Char>): HandTuples {
    val groups = hand.groupBy { it }
    return HandTuples(
        groups.filter { it.value.size == 2 }.size,
        groups.filter { it.value.size == 3 }.size,
        groups.filter { it.value.size == 4 }.size,
        groups.filter { it.value.size == 5 }.size,
        hand.filter { it == 'J' }.size
    )
}

class Day07 {
    fun evaluateHands(hands: Hands, values: Map<Char, Int>): Int {
        return hands.hands
            .sortedWith(compareBy(
                { it.type.value },
                // Ok, that's a bit cheap 🤷🏽‍♀️
                { values[it.hand[0]] },
                { values[it.hand[1]] },
                { values[it.hand[2]] },
                { values[it.hand[3]] },
                { values[it.hand[4]] }
            ))
            .mapIndexed { i, hand -> (i + 1) * hands.bids[hands.hands.indexOf(hand)] }
            .sum()
    }
}

data class Hands(val hands: List<Hand>, val bids: List<Int>)
data class Hand(val hand: List<Char>, val type: HandType)
data class HandTuples(
    val pairsCount: Int,
    val triplesCount: Int,
    val foursCount: Int,
    val fivesCount: Int,
    val jokersCount: Int
)

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

val handValuesWithJoker = mapOf(
    'A' to 14,
    'K' to 13,
    'Q' to 12,
    // Joker is lowest value
    'J' to 1,
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
