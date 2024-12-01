package task17

abstract class Rock(val fragments: Set<Fragment>, var x: Int, var y: Int) {

    fun maxX(): Int {
        return fragments.stream()
            .map { it.x + x }
            .max(Int::compareTo).orElseThrow()
    }

    fun minX(): Int {
        return x
    }

    fun minY(): Int {
        return fragments.stream()
            .map { it.y + y }
            .min(Int::compareTo).orElseThrow()
    }


}
