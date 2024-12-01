package task18b

import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val cubes = Files.lines(Path.of("src/main/resources/18.txt"))
        .map(String::trim)
        .map { it.split(",") }
        .map { Cube(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
        .toList()


    val ball = Ball(cubes)
    val surface = ball.surface()

    println("surface $surface")


}




