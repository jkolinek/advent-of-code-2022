package y2021

import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

fun main() {
    val map: List<List<Int>> = Files.lines(Path.of("src/main/resources/y2021/15.txt"))
        .map(String::trim)
        .map {
            it.toCharArray()
                .map { char -> char.digitToInt() }
                .toList()
        }
        .toList()

    val pathFinder = PathFinderB(map)
    pathFinder.find()
}
