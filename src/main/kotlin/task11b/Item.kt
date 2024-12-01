package task11b

data class Item(val base:Long, val multipliers: Set<Long> = setOf() ) {

    fun isDivisibleBy(divider: Long):Boolean {
        return base % divider == 0L ||
                multipliers.stream()
                    .anyMatch{it % divider == 0L}
    }
}
