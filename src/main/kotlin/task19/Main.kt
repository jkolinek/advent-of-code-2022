package task19

import java.nio.file.Files
import java.nio.file.Path
import java.util.Collections

fun main() {
    val regex =
        """Blueprint (\d+): Each ore robot costs (\d+) ore. Each clay robot costs (\d+) ore. Each obsidian robot costs (\d+) ore and (\d+) clay. Each geode robot costs (\d+) ore and (\d+) obsidian.""".toRegex()

    Files.lines(Path.of("src/main/resources/19.txt"))
        .map(String::trim)
        .map { regex.matchEntire(it) }
        .map { it!!.groupValues }
        .map { Blueprint(it[2].toInt(), it[3].toInt(), it[4].toInt(), it[5].toInt(), it[6].toInt(), it[7].toInt()) }
        .forEach {
            it.openGeodes()
        }


}



