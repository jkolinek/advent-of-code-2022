package task19

class ObsidianRobot: Step {
    override fun produce(): Supplies {
        return Supplies(obsidian = 1)
    }

    override fun cost(blueprint: Blueprint): Supplies {
        return Supplies(ore = blueprint.obsidianOre, clay = blueprint.obsidianClay)
    }

    companion object {
        fun lastMinuteToBuild(blueprint: Blueprint): Int {
            return 24 - blueprint.geodeObsidian - 1
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ObsidianRobot) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }


}
