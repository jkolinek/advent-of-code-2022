package task7b

data class Dir(val name: String, val parent: Dir?) {

    val files: MutableSet<File> = mutableSetOf()
    val dirs: MutableSet<Dir> = mutableSetOf()

    fun findDir(name: String): Dir? = dirs.find { it.name == name }


    override fun toString(): String {
        return "Dir(name='$name', files=$files, dirs=$dirs)"
    }


}
