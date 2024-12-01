package task19

class ClayRobot: Step {
    override fun produce(): Supplies {
        return Supplies(clay = 1)
    }

    override fun cost(blueprint: Blueprint): Supplies {
        return Supplies(ore = blueprint.clayOre)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ClayRobot) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    companion object {
        fun lastMinuteToBuild(blueprint: Blueprint): Int {
            return 24 - blueprint.obsidianClay - (24 - ObsidianRobot.lastMinuteToBuild(blueprint))
        }
    }

}
