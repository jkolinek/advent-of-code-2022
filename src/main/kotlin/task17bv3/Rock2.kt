package task17bv3

class Rock2(x: Int, y: Long) : Rock(setOf(Fragment(1, 2), Fragment(0, 1), Fragment(1, 1), Fragment(2, 1), Fragment(1, 0)), x, y) {
    override fun toString(): String {
        return "+($x, $y)"
    }
}
