package task12b

import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val map: List<List<Char>> = Files.lines(Path.of("src/main/resources/12.txt"))
        .map(String::trim)
        .map {
            it.toCharArray()
                .toList()
        }
        .toList()

    println(map)

    val pathFinder = PathFinder(map)
    pathFinder.find()
}
