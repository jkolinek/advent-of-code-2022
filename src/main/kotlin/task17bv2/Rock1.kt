package task17bv2

class Rock1(x: Int, y: Long) : Rock(setOf(RockPart(0, 0), RockPart(1, 0), RockPart(2, 0), RockPart(3, 0)), x, y) {
    override fun toString(): String {
        return "-($x, $y)"
    }

    override fun height(): Int {
        return 1
    }

    override fun width(): Int {
        return 4
    }
}
