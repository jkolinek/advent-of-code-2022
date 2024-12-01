package task16

data class Valve(val name:String, val rate: Int, val tunnels: MutableList<Valve> = mutableListOf(),
                 var pathRate:Int = 0, var timeElapsed:Int = 0) : Comparable<Valve>{
    override fun compareTo(other: Valve): Int {
        return this.pathRate compareTo other.pathRate
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Valve) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Valve(name='$name', rate=$rate, pathRate=$pathRate, timeElapsed=$timeElapsed)"
    }


}
