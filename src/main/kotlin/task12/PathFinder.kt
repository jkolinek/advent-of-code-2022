package task12

import java.lang.IllegalArgumentException
import java.util.ArrayDeque
import java.util.Deque

class PathFinder(private val map: List<List<Char>>) {
    private val xMax = map[0].size - 1
    private val yMax = map.size - 1
    private val toExplore: Deque<Area> = ArrayDeque()
    private val explored: MutableSet<Area> = mutableSetOf()


    fun find() {
        toExplore.add(findStart())
        while (toExplore.isNotEmpty()) {
            val next = toExplore.pop()
            toExplore.remove(next)
            println(next)
            toBeExplored(next.x - 1, next.y, next, next.length)
            toBeExplored(next.x, next.y + 1, next, next.length)
            toBeExplored(next.x, next.y - 1, next, next.length)
            toBeExplored(next.x + 1, next.y, next, next.length)

            explored.add(next);
            if (map[next.y][next.x] == 'E') {
                println("END ${next.length}")
                break
            }
        }
    }

    private fun findStart(): Area {
        map.forEachIndexed { indexRow, row ->
            row.forEachIndexed { indexCol, char ->
                if (char == 'S') {
                    val start = Area(indexCol, indexRow, 0)
                    println("Start $start")
                    return start
                }
            }
        }
        throw IllegalArgumentException()
    }

    private fun toBeExplored(x: Int, y: Int, current: Area, currentPathLength: Int) {
        if (x < 0 || x > xMax) return
        if (y < 0 || y > yMax) return
        if (explored.contains(Area(x, y))) return
        if ((getElevOf(x, y) - getElevOf(current) > 1)) return

        val newToExplore = Area(x, y, currentPathLength + 1)
        val existing = toExplore.find { it == newToExplore }
        println("existing $existing new $newToExplore")

        if (existing == null || existing.length > newToExplore.length) {
            println("adding to explore $newToExplore ")
            toExplore.add(newToExplore)
        }
    }

    private fun getElevOf(area: Area): Char {
        return getElevOf(area.x, area.y)
    }

    private fun getElevOf(x: Int, y: Int): Char {
        val elev = map[y][x]
        if (elev == 'S') return 'a'
        if (elev == 'E') return 'z'
        return elev
    }

}
