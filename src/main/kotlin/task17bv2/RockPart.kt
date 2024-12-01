package task17bv2

data class RockPart(val x: Int, val y: Long) {

    override fun toString(): String {
        return "($x,$y)"
    }
//
//    fun down(): Fragment {
//        return Fragment(x, y + 1)
//    }
//
//    fun left(): Fragment {
//        return Fragment(x - 1, y + 1)
//    }
//
//    fun right(): Fragment {
//        return Fragment(x + 1, y + 1)
//    }
}
