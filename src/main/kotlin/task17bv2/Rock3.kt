package task17bv2

class Rock3(x: Int, y: Long) : Rock(setOf(RockPart(0, 0), RockPart(1, 0), RockPart(2, 0), RockPart(2, 1), RockPart(2, 2)), x, y) {
    override fun toString(): String {
        return "⌟($x, $y)"
    }

    override fun height(): Int {
        return 3
    }

    override fun width(): Int {
        return 3
    }
}
