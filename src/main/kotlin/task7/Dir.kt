package task7

data class Dir(val name: String, val parent: Dir?) {

    val files: MutableSet<File> = mutableSetOf()
    val dirs: MutableSet<Dir> = mutableSetOf()

    fun findDir(name: String): Dir? = dirs.find { it.name == name }


    override fun toString(): String {
        return "Dir(name='$name', files=$files, dirs=$dirs)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Dir) return false

        if (name != other.name) return false
        if (parent != other.parent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (parent?.hashCode() ?: 0)
        return result
    }


}
