package y2021

class PathFinderB(private val map: List<List<Int>>) {
    private val tileWidth = map[0].size
    private val tileHeight = map.size
    private val xMax = tileWidth * 5 - 1
    private val yMax = tileHeight * 5 - 1
    private val finish = Area(xMax, yMax)
    private val toExplore: MutableSet<Area> = mutableSetOf()
    private val explored: MutableSet<String> = mutableSetOf()


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

            explored.add(safest.toCode());
            println("explored ${explored.size}")
            if (safest == finish) {
                println(safest.risk)
                break
            }
        }
    }

    private fun toBeExplored(x: Int, y: Int, currentPathRisk: Int) {
        if (x < 0 || x > xMax) return
        if (y < 0 || y > yMax) return
        if (explored.contains(Area(x, y).toCode())) return

        var areaRisk = (map[y % tileHeight][x % tileWidth] + (x / tileWidth) + (y / tileHeight))
        areaRisk = if(areaRisk % 9 > 0) areaRisk % 9 else areaRisk
        val risk = currentPathRisk + areaRisk
        val newToExplore = Area(x, y, risk)
        val existing = toExplore.find {it == newToExplore }
//        println("existing $existing new $newToExplore")

        if (existing == null || existing.risk > newToExplore.risk) {
//            println("adding to explore $newToExplore ")
            toExplore.add(newToExplore)
        }
    }

}
