package y2021

import java.util.Deque
import java.util.Queue
import java.util.TreeSet

class PathFinder(private val map: List<List<Int>>) {
    private val xMax = map[0].size - 1
    private val yMax = map.size - 1
    private val finish = Area(xMax, yMax)
    private val toExplore: MutableSet<Area> = mutableSetOf()
    private val explored: MutableSet<Area> = mutableSetOf()


    fun find() {
        toExplore.add(Area(0, 0, 0))
        while (toExplore.isNotEmpty()) {
            val safest = toExplore.min()
            toExplore.remove(safest)
            println(safest)
            toBeExplored(safest.x - 1, safest.y, safest.risk)
            toBeExplored(safest.x, safest.y + 1, safest.risk)
            toBeExplored(safest.x, safest.y - 1, safest.risk)
            toBeExplored(safest.x + 1, safest.y, safest.risk)

            explored.add(safest);
            if (safest == finish) {
                println(safest.risk)
                break
            }
        }
    }

    private fun toBeExplored(x: Int, y: Int, currentPathRisk: Int) {
        if (x < 0 || x > xMax) return
        if (y < 0 || y > yMax) return
        if (explored.contains(Area(x, y))) return

        val risk = currentPathRisk + map[y][x]
        val newToExplore = Area(x, y, risk)
        val existing = toExplore.find {it == newToExplore }
        println("existing $existing new $newToExplore")

        if (existing == null || existing.risk > newToExplore.risk) {
            println("adding to explore $newToExplore ")
            toExplore.add(newToExplore)
        }
    }

}
