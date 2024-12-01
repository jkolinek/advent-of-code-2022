import task7.Dir
import task7.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors.summingInt


fun main() {
    val regexDir = """^dir (.+)""".toRegex()
    val regexFile = """^([0-9]+) (.+)""".toRegex()
    val regexCommandCdUp = """^\$ cd \.\.$""".toRegex()
    val regexCommandCd = """^\$ cd (.+)""".toRegex()
    val regexCommandCdRoot = """^\$ cd /$""".toRegex()

    val root = Dir("/", null)
    var currentDir: Dir = root
    var totalTotal = 0
    val dirSizes: MutableMap<Dir, Int> = mutableMapOf()

    fun ifMatches(line: String, regex: Regex, function: (MatchGroupCollection) -> Unit): Boolean {
        if (regex.matches(line)) {
            function(regex.matchEntire(line)!!.groups)
            return true
        }
        return false
    }

    fun processLine(line: String?) {
        if (line == null) return
        ifMatches(line, regexDir) {
            currentDir.dirs.add(Dir(it[1]!!.value, currentDir))
        } ||
                ifMatches(line, regexFile) {
                    currentDir.files.add(File(it[2]!!.value, it[1]!!.value.toInt()))
                } ||
                ifMatches(line, regexCommandCdUp) {
                    if (currentDir.parent != null) {
                        currentDir = currentDir.parent!!
                    }
                } ||
                ifMatches(line, regexCommandCdRoot) {
                    currentDir = root
                } ||
                ifMatches(line, regexCommandCd) {
                    val name = it[1]!!.value
                    val dir = currentDir.findDir(name)
                    check(dir != null) { "${line}: dir $name not found in ${currentDir.name}" }
                    currentDir = dir
                }
    }

    fun calculateSizes(dir: Dir): Int {
        val size = dir.files.stream()
            .map { it.size }
            .collect(summingInt { it })

        val subSize = dir.dirs.stream()
            .map { calculateSizes(it) }
            .collect(summingInt { it })

        val total = size + subSize
        dirSizes[dir] = total

        println("dir ${dir.name} size $total")
        return total
    }

    Files.lines(Path.of("src/main/resources/7.txt"))
        .map(String::trim)
        .forEach { processLine(it) }

    val total = calculateSizes(root)
    val free = 70000000 - total;
    val needed = 30000000 - free

    val toDelete = dirSizes.entries.stream()
        .filter { it.value > needed }
        .sorted { o1, o2 -> o1.value.compareTo(o2.value) }
        .findFirst()

    println(toDelete)

}


private fun overlaps(pair: Pair<IntRange, IntRange>): Boolean {
    val intersection = pair.first.intersect(pair.second)
    return intersection.isNotEmpty()
}
