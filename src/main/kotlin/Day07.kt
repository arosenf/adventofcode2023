import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("HAnds not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName).toList()

    val result =
        if (args[1] == "part1") {
            -1
        } else {
            -1
        }

    println("Result: $result")
}

class Day07 {
}
