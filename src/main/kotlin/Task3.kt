import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import java.util.stream.Collectors.summingInt


fun main() {
    fun priority(char: Char): Int {
        val upperCase = char.isUpperCase()
        val lowercaseChar = char.lowercaseChar()
        val priority = lowercaseChar - 'a' + 1
        return if (upperCase) priority + 26 else priority
    }

    val sum = Files.lines(Path.of("src/main/resources/3.txt"))
        .map(String::trim)
        .flatMap { str -> findIntersection(str).stream() }
        .peek(::println)
        .map { char -> priority(char) }
        .peek(::println)
        .collect(summingInt { it })

    println(sum)

}

private fun findIntersection(str: String) =
    str.substring(0, str.length / 2).toSet().intersect(str.substring(str.length / 2).toSet())
