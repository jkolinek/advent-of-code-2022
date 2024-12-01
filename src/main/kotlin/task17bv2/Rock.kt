package task17bv2

abstract class Rock(val parts: Set<RockPart>, var x: Int, var y: Long, var down: Int = 0) {

    fun maxX(): Int {
        return x + width()
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
