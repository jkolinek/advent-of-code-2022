package task17bv2

class Rock2(x: Int, y: Long) : Rock(setOf(RockPart(1, 2), RockPart(0, 1), RockPart(1, 1), RockPart(2, 1), RockPart(1, 0)), x, y) {
    override fun toString(): String {
        return "+($x, $y)"
    }

    override fun height(): Int {
        return 3
    }

    override fun width(): Int {
        return 3
    }
}
