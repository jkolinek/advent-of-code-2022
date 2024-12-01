package task17bv3

abstract class Rock(val fragments: Set<Fragment>, var x: Int, var y: Long) {

    fun maxX(): Int {
        return fragments.stream()
            .map { it.x + x }
            .max(Int::compareTo).orElseThrow()
    }

    fun minX(): Int {
        return x
    }

    fun minY(): Long {
        return fragments.stream()
            .map { it.y + y }
            .min(Long::compareTo).orElseThrow()
    }


}
