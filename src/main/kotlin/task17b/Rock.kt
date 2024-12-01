package task17b

abstract class Rock(val fragments: Set<Fragment>, var x: Int, var y: Long, var down: Int = 0) {

    fun maxX(): Int {
        return x + width() -1
    }

    fun minX(): Int {
        return x
    }

    fun minY(): Long {
        return y
    }

    fun moveDown() {
        y--
        down++
    }

    abstract fun height(): Int

    abstract fun width(): Int


}
