package task19

import java.util.stream.Stream

data class Blueprint(
    val oreOre: Int,
    val clayOre: Int,
    val obsidianOre: Int, val obsidianClay: Int,
    val geodeOre: Int, val geodeObsidian: Int
) {
    fun openGeodes() {
        println(nextMinute(listOf(), None()))
    }

    fun nextMinute(prevSteps: List<Step>, next: Step): Int {
        val supplies = supplies(prevSteps)
        if (!supplies.hasEnough(next.cost(this))) {
            print("A")
            return Int.MIN_VALUE
        }
        if (isWrongWay(prevSteps)) {
            print("B")
            return Int.MIN_VALUE
        }
        if (supplies.obsidian >= geodeObsidian && next !is GeodeRobot) {
            print("C")
            return Int.MIN_VALUE
        }
        val steps: MutableList<Step> = prevSteps.toMutableList()
        steps.add(next)

        if (steps.size == 24) {
            val geodes = supplies(steps).geode
//            print(geodes)
            return geodes
        }

        return Stream.of(
            nextMinute(steps, None()),
            nextMinute(steps, OreRobot()),
            nextMinute(steps, ClayRobot()),
            nextMinute(steps, ObsidianRobot()),
            nextMinute(steps, GeodeRobot())
        ).max(Int::compareTo).orElseThrow()
    }

    private fun isWrongWay(steps: List<Step>): Boolean {
        return !steps.contains(ClayRobot()) && ClayRobot.lastMinuteToBuild(this) < steps.size
                || !steps.contains(ObsidianRobot()) && ObsidianRobot.lastMinuteToBuild(this) < steps.size
                || !steps.contains(GeodeRobot()) && GeodeRobot.lastMinuteToBuild(this) < steps.size
    }

    fun supplies(steps: List<Step>): Supplies {
        val supplies = Supplies()
        val robots: MutableList<Step> = mutableListOf(OreRobot())

        for (step in steps) {
            robots.forEach {
                supplies.add(it.produce())
            }
            supplies.take(step.cost(this))
            robots.add(step)
        }
        return supplies
    }

}
