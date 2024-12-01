import java.nio.file.Files
import java.nio.file.Path


fun main() {
    val regex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

    val count = Files.lines(Path.of("src/main/resources/4.txt"))
        .map(String::trim)
        .map { regex.matchEntire(it) }
        .map { result ->
            IntRange(result!!.groupValues[1].toInt(), result!!.groupValues[2].toInt()) to
                    IntRange(result!!.groupValues[3].toInt(), result!!.groupValues[4].toInt())
        }
        .filter { pair -> overlaps(pair) }
        .peek(::println)
        .count()

    println(count)
}

private fun overlaps(pair: Pair<IntRange, IntRange>): Boolean {
    val intersection = pair.first.intersect(pair.second)
    return intersection.isNotEmpty()
}
