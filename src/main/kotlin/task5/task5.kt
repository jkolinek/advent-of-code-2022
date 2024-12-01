import task5.Move
import java.nio.file.Files
import java.nio.file.Path
import java.util.ArrayDeque
import java.util.stream.Collectors


fun main() {
    val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()

    val moves = Files.lines(Path.of("src/main/resources/5.txt"))
        .map(String::trim)
        .map { regex.matchEntire(it) }
        .map { result ->
            val count = result!!.groupValues[1].toInt()
            val from = result.groupValues[2]
            val to = result.groupValues[3]
            Move(from, to, count)
        }
        .peek(::println)
        .toList()

    val stacks = mapOf(
        "1" to ArrayDeque(listOf("G", "P", "N", "R")),
        "2" to ArrayDeque(listOf("H", "V", "S", "C", "L", "B", "J", "T")),
        "3" to ArrayDeque(listOf("L", "N", "M", "B", "D", "T")),
        "4" to ArrayDeque(listOf("B", "S", "P", "V", "R")),
        "5" to ArrayDeque(listOf("H", "V", "M", "W", "S", "Q", "C", "G")),
        "6" to ArrayDeque(listOf("J", "B", "D", "C", "S", "Q", "W")),
        "7" to ArrayDeque(listOf("L", "Q", "F")),
        "8" to ArrayDeque(listOf("V", "F", "L", "D", "T", "H", "M", "W")),
        "9" to ArrayDeque(listOf("F", "J", "M", "V", "B", "P", "L")),
    )

    println(stacks)
    for (move in moves) {
        val items = (1 .. move.count).map { stacks[move.from]!!.pop() } .toList()
        items.reversed().forEach { stacks[move.to]!!.push(it) }
        println(stacks)
    }

    val tops = stacks.values.stream()
        .map { it.peekFirst() }
        .collect(Collectors.joining())

    println(tops)

}

