import java.io.Console
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors


fun main() {
    val scoresByResult = mapOf(
        ("A" to "X") to 3, ("A" to "Y") to 6, ("A" to "Z") to 0,
        ("B" to "X") to 0, ("B" to "Y") to 3, ("B" to "Z") to 6,
        ("C" to "X") to 6, ("C" to "Y") to 0, ("C" to "Z") to 3,
    )
    val scoresByType = mapOf("X" to 1, "Y" to 2, "Z" to 3)

    val score = Files.lines(Path.of("src/main/resources/2.txt"))
        .map(String::trim)
        .map { it.split(" ") }
        .map { line -> scoresByType[line[1]]!! + scoresByResult[Pair(line[0], line[1])]!! }
        .peek(::println)
        .reduce(0) { a, b -> a + b }

    println(score)

}
