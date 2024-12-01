package y2021

data class Area(val x:Int, val y:Int, val risk: Int = 0) : Comparable<Area>{
    override fun compareTo(other: Area): Int {
        return this.risk compareTo other.risk
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Area

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    fun toCode() = "${x}|${y}"

}
