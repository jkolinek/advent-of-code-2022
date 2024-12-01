package task17bv2

data class StoppedFragment(val x: Int) {

    override fun toString(): String {
        return "$x"
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
