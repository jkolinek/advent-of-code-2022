package task17

class Rock3(x: Int, y: Int) : Rock(setOf(Fragment(0, 0), Fragment(1, 0), Fragment(2, 0), Fragment(2, 1), Fragment(2, 2)), x, y) {
    override fun toString(): String {
        return "⌟($x, $y)"
    }
}
