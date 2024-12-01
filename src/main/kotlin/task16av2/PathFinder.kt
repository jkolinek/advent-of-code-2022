package task16av2

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import java.util.stream.Collectors.toSet

class PathFinder(val valves: Set<Valve>) {
    val valveCount = valves.size.toLong()
    val nonZeroValves = valves.stream().filter { it.rate > 0 }.collect(toSet())
    var bestPath: AtomicReference<Collection<WayPoint>> = AtomicReference(listOf())
    var bestPathScore = AtomicInteger(0)
    val visited: MutableMap<Visited, Int> = ConcurrentHashMap()


    fun find(path: List<WayPoint>) {
//        println(path)
        val last = path.last()
//        println("$last")
        if (last.time >= 30) {
            discovered(path)
            return
        }

        val openedValves = path.stream().filter() { it.open }
            .map { it.valve }
            .collect(toSet())
        if (openedValves.containsAll(nonZeroValves)) {
//            println("all open")
            discovered(path)
            return
        }
//        val remaining = valves.subtract(openedValves)
//        val allZero = remaining.stream().allMatch() { it.rate == 0 }
//        if (allZero) {
////            println("all zero $allZero")
//            discovered(path)
//            return
//        }
        if (last.open) {
            val newVisited = Visited(last, last.time)
            val visitedBeforeScore = visited[newVisited]
            if (visitedBeforeScore != null && visitedBeforeScore > score(path)) {
                val score = score(path)
//                println("already visited $newVisited $visitedBeforeScore < $score")
                return
            }
            visited[newVisited] = score(path)
        }


        val lastOpenIndex = path.indexOfLast { it.open }
        val closedTailIndex = if (lastOpenIndex == -1) 0 else lastOpenIndex + 1
        val lastNClosed = if (path.lastIndex + 1 > closedTailIndex) {
            path.subList(closedTailIndex, path.lastIndex + 1)
        } else {
            listOf()
        }
        last.valve.tunnels.parallelStream()
            .forEach {
                goTo(path, WayPoint(it, true, last.time + 2))
                if (!lastNClosed.contains(WayPoint(it, false))) {
                    goTo(path, WayPoint(it, false, last.time + 1))
                }
            }
    }

    private fun discovered(path: List<WayPoint>) {
        var score = score(path)
        if (score > bestPathScore.get()) {
            bestPathScore.set(score)
            bestPath.set(path)
            println("bestPathScore $bestPathScore")
            println("bestPath $bestPath")
            println()
        }
    }

    private fun score(path: List<WayPoint>): Int {
        var score = 0
        var time = -1
        path.forEach() { wayPoint ->
            time++
            if (wayPoint.open) {
                time++
                score += wayPoint.valve.rate * (30 - time)
            }
        }
        return score
    }

    fun goTo(path: List<WayPoint>, next: WayPoint) {
//        println(next.valve.rate)
        if (next.open && path.contains(next)) return
        val pathCopy = path.toMutableList()
        pathCopy.add(next)
        find(pathCopy)
    }
}
