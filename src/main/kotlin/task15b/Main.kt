package task15b

import java.lang.RuntimeException
import java.nio.file.Files
import java.nio.file.Path
import java.util.NoSuchElementException
import java.util.stream.Collectors.toMap
import kotlin.math.abs

fun main() {
    val regex = """Sensor at x=([-\d]+), y=([-\d]+): closest beacon is at x=([-\d]+), y=([-\d]+)""".toRegex()

    val pairs = Files.lines(Path.of("src/main/resources/15.txt"))
        .map { regex.matchEntire(it) }
        .map { result ->
            val g = result!!.groupValues
            Point(g[1].toInt(), g[2].toInt()) to Point(g[3].toInt(), g[4].toInt())
        }
        .collect(toMap({ it.first }, { it.second }))

    val distancesFromSensor = pairs.entries.stream()
        .collect(toMap({ it.key }, { (s, b) -> dist(s, b) }))

    val maxDist = distancesFromSensor.values.stream()
        .max(Int::compareTo).orElseThrow()
    val xMax = pairs.keys.stream()
        .map { it.x }
        .max(Int::compareTo).orElseThrow() + maxDist
    val xMin = pairs.keys.stream()
        .map { it.x }
        .min(Int::compareTo).orElseThrow() - maxDist


    try {
        find(distancesFromSensor)
    } catch (e:UncoveredException) {
        println("FOUND ${e.point}")
        println(e.point.x.toLong() * LIMIT + e.point.y)
    }
}

private fun find(distancesFromSensor: MutableMap<Point, Int>): Point {
    distancesFromSensor.entries.parallelStream()
        .forEach { (s, d) ->
            println("Checking sensor $s, dist $d")
            val outerDist = d + 1
            for (x in -outerDist..outerDist) {
                covered(Point(s.x + x, s.y + (outerDist - abs(x))), distancesFromSensor)
                covered(Point(s.x + x, s.y - (outerDist - abs(x))), distancesFromSensor)
            }
        }
    throw NoSuchElementException()
}

private const val LIMIT = 4000000

fun covered(point: Point, distancesFromSensor: MutableMap<Point, Int>) {
    if (point.x > LIMIT || point.y > LIMIT || point.x < 0 || point.y < 0) return
    val anyMatch = distancesFromSensor.entries.parallelStream()
        .anyMatch { (s, d) ->
            dist(point, s) <= d
        }
    if (!anyMatch) {
        //println("FOUND $point")
        throw UncoveredException(point)
    }
}

private fun dist(a: Point, b: Point) = abs(a.x - b.x) + abs(a.y - b.y)


