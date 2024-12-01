package task16av2

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors.toSet

fun main() {
    val regex = """Valve ([A-Z]+) has flow rate=(\d+); tunnels? leads? to valves? ([A-Z ,]+)""".toRegex()


    val valves = Files.lines(Path.of("src/main/resources/16.txt"))
        .filter{
            it.isNotBlank()
        }
        .map { regex.matchEntire(it) }
        .map { result ->
            val g = result!!.groupValues
            Valve(g[1], g[2].toInt())
        }
        .collect(toSet())

    val valvesByName = valves.groupBy { it.name }

    Files.lines(Path.of("src/main/resources/16.txt"))
        .map { regex.matchEntire(it) }
        .forEach { result ->
            val g = result!!.groupValues
            g[3].split(", ").forEach{
                valvesByName[g[1]]!![0]!!.tunnels.add(valvesByName[it]!![0])
            }
        }

    val start = valvesByName["AA"]!![0]
    val pathFinder = PathFinder(valves)
    pathFinder.find(listOf(WayPoint(start, false)))

    println(pathFinder.bestPath)
    println(pathFinder.bestPathScore)

}
