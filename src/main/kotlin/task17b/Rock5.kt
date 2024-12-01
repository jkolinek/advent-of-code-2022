package task17b

class Rock5(x: Int, y: Long) : Rock(setOf(Fragment(0, 0), Fragment(1, 0), Fragment(0, 1), Fragment(1, 1)), x, y) {
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
