package task16

class PathFinder() {
    private val toExplore: MutableSet<Valve> = mutableSetOf()
    private val explored: MutableSet<Valve> = mutableSetOf()


    fun find(start: Valve) {
        start.timeElapsed = 0
        start.pathRate = start.rate * (30 - start.timeElapsed)
        toExplore.add(start)
        while (toExplore.isNotEmpty()) {
            val best = toExplore.max()
            toExplore.remove(best)
            println("opening $best")
            best.tunnels.forEach {
                toBeExplored(it, best)
            }

            explored.add(best);
            if (toExplore.isEmpty()) {
                println(best.pathRate)
                break
            }
        }
    }

    private fun toBeExplored(valve: Valve, from: Valve) {
        if(from.timeElapsed + 2 > 30) return
        if (explored.contains(valve)) return

        valve.pathRate = from.pathRate + (valve.rate * (30 - from.timeElapsed + 2))
        val existing = toExplore.find { it == valve }
//        println("existing $existing new $valve")

        if (existing == null || existing.pathRate < valve.pathRate) {
            valve.timeElapsed = from.timeElapsed + 2
            println("adding to explore $valve ")
            toExplore.add(valve)
        }
    }

}
