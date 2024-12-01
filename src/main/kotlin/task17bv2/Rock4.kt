package task17bv2

class Rock4(x: Int, y: Long) : Rock(setOf(RockPart(0, 0), RockPart(0, 1), RockPart(0, 2), RockPart(0, 3)), x, y) {
    override fun toString(): String {
        return "|($x, $y)"
    }

    override fun height(): Int {
        return 4
    }

    override fun width(): Int {
        return 1
    }
}
