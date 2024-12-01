package task19

class OreRobot: Step {
    override fun produce(): Supplies {
        return Supplies(ore = 1)
    }

    override fun cost(blueprint: Blueprint): Supplies {
        return Supplies(ore = blueprint.oreOre)
    }
}
