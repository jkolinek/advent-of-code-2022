package task17

class Rock5(x: Int, y: Int) : Rock(setOf(Fragment(0, 0), Fragment(1, 0), Fragment(0, 1), Fragment(1, 1)), x, y) {
    override fun toString(): String {
        return "□($x, $y)"
    }
}
