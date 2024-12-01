package task14b

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val shapes = Files.lines(Path.of("src/main/resources/14.txt"))
        .map { it.split(" -> ") }
        .map { listOfPoints ->
            listOfPoints.stream()
                .map { it.split(",") }
                .map { Point(it[0].toInt(), it[1].toInt()) }
                .toList()
        }
        .toList()

    val plan: MutableMap<Point, Item> = drawRocks(shapes)

    val yMax = plan.keys.stream()
        .map { it.y }
        .max(Int::compareTo).orElseThrow()
    val yFloor = yMax + 2

    var full = false
    while (!full) {
        val start = Point(500, 0)
        var pos = start
        var stuck = false;
        while (!stuck) {
            if (plan[start] is Sand) {
                full = true
                break
            }
            if (plan[pos.down()] == null && pos.down().y < yFloor) {
                pos = pos.down()
                continue
            } else {
                if (plan[pos.downLeft()] == null && pos.down().y < yFloor) {
                    pos = pos.downLeft()
                    continue
                } else {
                    if (plan[pos.downRight()] == null && pos.down().y < yFloor) {
                        pos = pos.downRight()
                        continue
                    } else {
                        stuck = true
                        plan[pos] = Sand()
                    }
                }
            }
        }
    }
    println(plan)

    val sandCount = plan.values.stream()
        .filter { it is Sand }
        .count()

    println(sandCount)

    print(plan)
}

private fun print(plan: MutableMap<Point, Item>) {
    val yMax = plan.keys.stream()
        .map { it.y }
        .max(Int::compareTo).orElseThrow()
    val xMax = plan.keys.stream()
        .map { it.x }
        .max(Int::compareTo).orElseThrow()

    val xMin = plan.keys.stream()
        .map { it.x }
        .min(Int::compareTo).orElseThrow()
    println("$xMin - $xMax, 0 - $yMax")

    for (y in 0 .. yMax) {
        for (x in xMin..xMax) {
            val point = Point(x, y)
            if (plan[point] == null) {
                print('.')
            } else {
                print(plan[point])
            }
        }
        println()
    }
}


private fun drawRocks(shapes: MutableList<MutableList<Point>>): MutableMap<Point, Item> {
    val plan: MutableMap<Point, Item> = mutableMapOf()
    shapes.forEach { line ->
        line.windowed(2, 1).forEach { (a, b) ->
            if (a.x == b.x) {
                val vector = b.y - a.y
                for (y in 0..abs(vector)) {
                    putRock(plan, a.x, a.y + y * vector.sign)
                }
            } else if (a.y == b.y) {
                val vector = b.x - a.x
                for (x in 0..abs(vector)) {
                    putRock(plan, a.x + x * vector.sign, a.y)
                }
            } else {
                throw IllegalArgumentException("not straight")
            }
        }
    }
    return plan
}

private fun putRock(
    plan: MutableMap<Point, Item>,
    x: Int,
    y: Int
) {
    val point = Point(x, y)
    plan[point] = Rock()
}
