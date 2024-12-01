package task17b

class Rock1(x: Int, y: Long) : Rock(setOf(Fragment(0, 0), Fragment(1, 0), Fragment(2, 0), Fragment(3, 0)), x, y) {
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
