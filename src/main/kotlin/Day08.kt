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
            -1
        }

    println("Result: $result")
}

class Day08 {
    fun navigate(input: Sequence<String>): Int {
        val inputList = input.toList()
        val instructions = parseInstructions(inputList.first())
        val network = parseNetwork(inputList)

        var result = 0
        var currentNode = network["AAA"]

        // That's actually very risky and could produce an infinite loop
        run breaking@{
            instructions.forEach {
                result += 1
                val newNode =
                    when (it) {
                        'L' -> currentNode!!.left
                        'R' -> currentNode!!.right
                        else -> "ZZZ"
                    }

                currentNode = network[newNode]
                if (newNode == "ZZZ") {
                    return@breaking
                }
            }
        }

        return result
    }

    fun navigateGhost(input: Sequence<String>): Int {
        val inputList = input.toList()
        val instructions = parseInstructions(inputList.first())
        val network = parseNetwork(inputList)

        var result = 0
        var currentNodes = findStartingNodesForGhost(network)

        // That's actually very risky and could produce an infinite loop
        run breaking@{
            instructions.forEach { ins ->
                result += 1
                val newNodes = currentNodes.map { n ->
                    val newNode =
                        when (ins) {
                            'L' -> n.left
                            'R' -> n.right
                            else -> "ZZZ"
                        }
                    network[newNode]!!
                }

                currentNodes = newNodes
                val zNodeCount = currentNodes.count { it.name.endsWith('Z') }
                if (zNodeCount >= currentNodes.size) {
                    return@breaking
                }
            }
        }

        return result
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

    data class Node(val name: String, val left: String, val right: String)
}
