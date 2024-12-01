package task17bv3

class Rock4(x: Int, y: Long) : Rock(setOf(Fragment(0, 0), Fragment(0, 1), Fragment(0, 2), Fragment(0, 3)), x, y) {
    override fun toString(): String {
        return "|($x, $y)"
    }
}
