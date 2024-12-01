package task19

data class Supplies(var ore: Int = 0, var clay: Int = 0, var obsidian: Int = 0, var geode: Int = 0) {

    fun add(addition: Supplies) {
        ore += addition.ore
        clay += addition.clay
        obsidian += addition.obsidian
        geode += addition.geode
    }

    fun take(cost: Supplies) {
        ore -= cost.ore
        clay -= cost.clay
        obsidian -= cost.obsidian
    }

    fun hasEnough(cost: Supplies): Boolean {
        return ore >= cost.ore &&
                clay >= cost.clay &&
                obsidian >= cost.obsidian
    }
}
