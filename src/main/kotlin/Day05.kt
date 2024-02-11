import kotlin.math.min
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() or (args.size < 2)) {
        println("Almanac not specified")
        exitProcess(1)
    }
    val fileName = args.first()
    println("Reading $fileName")
    val lines = readLines(fileName)

    val result =
        if (args[1] == "part1") {
            Day05().findLowestLocation1(lines)
        } else {
            Day05().findLowestLocation2(lines)
        }

    println("Result: $result")
}

class Day05 {
    fun findLowestLocation1(lines: Sequence<String>): Long {
        val almanac = readAlmanac(lines)
        return processAlmanac(almanac)
    }

    fun findLowestLocation2(lines: Sequence<String>): Long {
        val almanac = expandSeeds(readAlmanac(lines))
        return processAlmanac(almanac)
    }

    private fun processAlmanac(almanac: Almanac): Long {
        var result = Long.MAX_VALUE
        almanac.seeds.forEach { seed ->
            var finalSeed = seed
            almanac.maps.forEach { map ->
                finalSeed = propagateSeed(finalSeed, map.ranges)
            }
            result = min(result, finalSeed)
        }
        return result
    }

    // We read everything and keep it in memory. No need to do stream processing, we got RAM! 💾
    private fun readAlmanac(lines: Sequence<String>): Almanac {
        var seeds: Sequence<Long> = emptySequence()
        val almanacMaps: List<AlmanacMap> = arrayListOf()

        var currentRanges: List<Range> = arrayListOf()
        for (line in lines) {
            when {
                line.isEmpty() -> {
                    continue
                }

                line.startsWith("seeds: ") -> {
                    seeds = line.substringAfter(':')
                        .trim()
                        .splitToSequence(' ')
                        .map(String::toLong)
                }

                line.endsWith("map:") -> {
                    almanacMaps.addLast(AlmanacMap(currentRanges.asSequence()))
                    currentRanges = arrayListOf()
                }

                else -> {
                    val parsedRange = line.splitToSequence(' ')
                        .map(String::toLong)
                        .toList()
                    currentRanges.addLast(Range(parsedRange[0], parsedRange[1], parsedRange[2]))
                }
            }
        }
        almanacMaps.addLast(AlmanacMap(currentRanges.asSequence()))

        return Almanac(seeds, almanacMaps.asSequence())
    }

    private fun propagateSeed(seed: Long, ranges: Sequence<Range>): Long {
        ranges.forEach {
            if (seed >= it.sourceRangeStart && seed < it.sourceRangeStart + it.rangeLength) {
                return it.destinationRangeStart + (seed - it.sourceRangeStart)
            }
        }
        return seed
    }

    private fun expandSeeds(almanac: Almanac): Almanac {
        return Almanac(
            almanac.seeds
                .chunked(2)
                .map {
                    generateSequence(it.first()) { it + 1 }
                        .take(it.last().toInt())
                }
                .flatten(),
            almanac.maps
        )
    }

    data class Almanac(val seeds: Sequence<Long>, val maps: Sequence<AlmanacMap>)
    data class AlmanacMap(val ranges: Sequence<Range>)
    data class Range(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long)
}
