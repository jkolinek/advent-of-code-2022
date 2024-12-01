package task16av2

import java.util.SortedSet

data class Valve(val name:String, val rate: Int, val tunnels: MutableSet<Valve> = mutableSetOf(),
                 var timeElapsed:Int = 0) : Comparable<Valve>{
    override fun compareTo(other: Valve): Int {
        return this.rate compareTo other.rate
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
        return "Valve(name='$name', rate=$rate, timeElapsed=$timeElapsed)"
    }


}
