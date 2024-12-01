package task10

import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs


fun main() {
//    val noopRegex = """noop""".toRegex()
    val addRegex = """addx ([-0-9]+)""".toRegex()


    var reg = 1
    var cycle = 0

    fun cycle() {

        val pixelIndex = cycle % 40
        if (pixelIndex == 0) {
            println()
        }
        if (abs(reg - pixelIndex) <= 1) {
            print("#")
        } else {
            print(".")
        }
        cycle++
    }

    Files.lines(Path.of("src/main/resources/10.txt"))
        .map(String::trim)
        .toList()
        .forEach { cmd ->
            val match = addRegex.matchEntire(cmd)
            if (match != null) {
                cycle()
                cycle()
                reg += match.groups[1]!!.value.toInt()
            } else {
                cycle()
            }
        }
}


