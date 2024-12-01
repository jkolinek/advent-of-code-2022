package task16av2

data class WayPoint(val valve: Valve, val open: Boolean, var time: Int = 0) {

    override fun toString(): String {
        return " -> (${valve.name} $open)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WayPoint) return false

        if (valve != other.valve) return false
        if (open != other.open) return false

        return true
    }

    override fun hashCode(): Int {
        var result = valve.hashCode()
        result = 31 * result + open.hashCode()
        return result
    }


}
