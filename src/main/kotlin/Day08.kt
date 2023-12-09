import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Hands not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName)

    val result =
        if (args[1] == "part1") {
            Day08().navigate(lines)
        } else {
            Day08().navigateGhost(lines)
        }

    println("Result: $result")
}

class Day08 {
    fun navigate(input: Sequence<String>): Long {
        val inputList = input.toList()
        val instructions = parseInstructions(inputList.first())
        val network = parseNetwork(inputList)
        val startNode = network["AAA"]!!

        return findPathLength(startNode, "ZZZ", instructions, network)
    }

    fun navigateGhost(input: Sequence<String>): Long {
        val inputList = input.toList()
        val network = parseNetwork(inputList)
        val startNodes = findStartingNodesForGhost(network)

        val results = startNodes.map {
            findPathLength(
                it,
                "Z",
                parseInstructions(inputList.first()),
                network
            )
        }.toList()

        return lcm(results)
    }

    private fun parseInstructions(input: String): Sequence<Char> {
        val instruction = input.toCharArray().toList()
        //.take() as many as you want, this is an infinitely looping sequence
        return generateSequence { instruction }.flatten()
    }

    private fun parseNetwork(input: List<String>): Map<String, Node> {
        val network = mutableMapOf<String, Node>()
        input.drop(2)
            .forEach {
                val node = parseNode(it)
                network[node.first] = node.second
            }
        return network
    }

    private fun parseNode(node: String): Pair<String, Node> {
        val key = parseKey(node)
        val rawNode = node.substringAfter('=')
            .trim()
            .substringAfter('(')
            .substringBefore(')')
            .split(',')
            .map { s -> s.trim() }
        return key to Node(key, rawNode.first(), rawNode.last())
    }

    private fun parseKey(node: String): String {
        return node.substringBefore('=').trim()
    }

    private fun findStartingNodesForGhost(network: Map<String, Node>): List<Node> {
        return network.keys
            .filter { it.endsWith('A') }
            .map { network[it]!! }
            .toList()
    }

    // We assume the path will eventually come to an end
    private fun findPathLength(
        startNode: Node,
        endCondition: String,
        instructions: Sequence<Char>,
        network: Map<String, Node>
    ): Long {
        var result = 0L
        var currentNode = startNode
        run breaking@{
            instructions.forEach {
                result += 1
                val newNode =
                    when (it) {
                        'L' -> currentNode.left
                        'R' -> currentNode.right
                        else -> endCondition
                    }

                currentNode = network[newNode]!!
                if (newNode.endsWith(endCondition)) {
                    return@breaking
                }
            }
        }
        return result
    }

    private fun lcm(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1..<numbers.size) {
            result = lcm(result, numbers[i])
        }
        return result
    }

    private fun lcm(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }

    data class Node(val name: String, val left: String, val right: String)
}
