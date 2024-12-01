package task10

import java.nio.file.Files
import java.nio.file.Path


fun main() {
//    val noopRegex = """noop""".toRegex()
    val addRegex = """addx ([-0-9]+)""".toRegex()


    var reg = 1
    var total = 0
    var cycle = 1

    fun cycle() {
        cycle++
        if (cycle <= 220 && (cycle == 20 || (cycle + 20) % 40 == 0)) {
            val signal = reg * cycle
            total += signal
            println("$cycle: reg $reg, signal $signal")
        }
    }

    Files.lines(Path.of("src/main/resources/10.txt"))
        .map(String::trim)
        .toList()
        .forEach { cmd ->
            val match = addRegex.matchEntire(cmd)
            if (match != null) {
                cycle()
                reg += match.groups[1]!!.value.toInt()
            }
            cycle()
        }

    println(total)
}


