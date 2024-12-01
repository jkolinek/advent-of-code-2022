package task17bv3

class Rock1(x: Int, y: Long) : Rock(setOf(Fragment(0, 0), Fragment(1, 0), Fragment(2, 0), Fragment(3, 0)), x, y) {
    override fun toString(): String {
        return "-($x, $y)"
    }
}
