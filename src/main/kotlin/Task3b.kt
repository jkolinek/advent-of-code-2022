import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import java.util.stream.Collectors.summingInt


fun main() {


    val sum = Files.lines(Path.of("src/main/resources/3.txt"))
        .map(String::trim)
        .toList()
        .windowed(3, 3, true)
        .stream()
        .map { group -> findIntersection(group) }
        .peek(::println)
        .flatMap { it.stream() }
        .map { char -> priority(char) }
        .peek(::println)
        .collect(summingInt { it })

    println(sum)

}

private fun priority(char: Char): Int {
    val upperCase = char.isUpperCase()
    val lowercaseChar = char.lowercaseChar()
    val priority = lowercaseChar - 'a' + 1
    return if (upperCase) priority + 26 else priority
}

private fun findIntersection(group: List<String>) =
    group[0].toSet() intersect group[1].toSet() intersect group[2].toSet()

