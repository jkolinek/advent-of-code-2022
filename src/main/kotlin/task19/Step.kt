package task19

interface Step {
    fun produce(): Supplies
    fun cost(blueprint: Blueprint): Supplies
}
