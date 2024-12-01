package task15b

data class Point(val x: Int, val y: Int) {

    override fun toString(): String {
        return "($x,$y)"
    }

    fun down(): Point {
        return Point(x, y + 1)
    }

    fun downLeft(): Point {
        return Point(x - 1, y + 1)
    }

    fun downRight(): Point {
        return Point(x + 1, y + 1)
    }
}
