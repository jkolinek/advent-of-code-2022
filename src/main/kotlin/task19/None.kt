package task19

class None : Step {
    override fun produce(): Supplies {
        return Supplies()
    }

    override fun cost(blueprint: Blueprint): Supplies {
        return Supplies()
    }
}
