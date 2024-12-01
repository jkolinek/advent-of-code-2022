package task15

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors.toMap
import java.util.stream.Collectors.toSet
import java.util.stream.Stream
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


    var count = 0
    println("x $xMin to $xMax")
    for (x in xMin..xMax) {
        val point = Point(x, 2000000)
        val covered = distancesFromSensor.entries.stream()
            .anyMatch {
                dist(point, it.key) <= it.value
            }
        count += if (covered && !pairs.values.contains(point)) 1 else 0
        print(if (covered) '#' else '.')
    }

    println(count)


}

private fun dist(a: Point, b: Point) = abs(a.x - b.x) + abs(a.y - b.y)


