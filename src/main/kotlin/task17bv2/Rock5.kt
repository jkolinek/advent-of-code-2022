package task17bv2

class Rock5(x: Int, y: Long) : Rock(setOf(RockPart(0, 0), RockPart(1, 0), RockPart(0, 1), RockPart(1, 1)), x, y) {
    override fun toString(): String {
        return "□($x, $y)"
    }

    override fun height(): Int {
        return 2
    }

    override fun width(): Int {
        return 2
    }
}
