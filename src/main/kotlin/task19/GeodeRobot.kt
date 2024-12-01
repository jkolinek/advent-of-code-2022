package task19

class GeodeRobot:  Step {
    override fun produce(): Supplies {
        return Supplies(geode = 1)
    }

    override fun cost(blueprint: Blueprint): Supplies {
        return Supplies(ore = blueprint.geodeOre, obsidian = blueprint.geodeObsidian)
    }

    companion object {
        fun lastMinuteToBuild(blueprint: Blueprint): Int {
            return 23
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GeodeRobot) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }


}
