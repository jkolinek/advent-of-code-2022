import java.nio.file.Files
import java.nio.file.Path


fun main() {
    val plan = mapOf(
        ("A" to "X") to "C", ("A" to "Y") to "A", ("A" to "Z") to "B",
        ("B" to "X") to "A", ("B" to "Y") to "B", ("B" to "Z") to "C",
        ("C" to "X") to "B", ("C" to "Y") to "C", ("C" to "Z") to "A",
    )
    val scoresByResult = mapOf("X" to 0, "Y" to 3, "Z" to 6)
    val scoresByMove = mapOf("A" to 1, "B" to 2, "C" to 3)

    val score = Files.lines(Path.of("src/main/resources/2.txt"))
        .map(String::trim)
        .map { str -> str.split(" ") }
        .map { line ->
            val move = plan[Pair(line[0], line[1])]!!
            scoresByResult[line[1]]!! + scoresByMove[move]!!
        }
        .peek(::println)
        .reduce(0) { a, b -> a + b }

    println(score)

}
