package task13

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val packets = Files.lines(Path.of("src/main/resources/13.txt"))
        .map(String::trim)
        .filter(String::isNotBlank)
        .map { l -> Array(processInput(l)) }
        .toList()

    println(packets)

    var ok = 0
    for (i in 0 until packets.size step 2) {
        val result = packets[i].compareTo(packets[i + 1])
        println("checking $i compared $result")
        println()
        if(result == -1) ok += (i / 2) + 1
    }

    println(ok)
}

fun processInput(input: String): List<Item> {
    val items = mutableListOf<Item>()
    var i = 0
    while (i < input.length) {
        if (input[i] == '[') {
            val endSub = indexOfClosing(input, i)
            val inner = input.substring(i + 1, endSub)
            items.add(Array(processInput(inner)))
            i = endSub + 1
        }
        else if(input[i] == ',') i++
        else if (input[i].isDigit()) {
            var numberEndIndex = input.substring(i).indexOfFirst { c -> !c.isDigit() }
            numberEndIndex = if(numberEndIndex == -1) input.lastIndex + 1 else numberEndIndex + i
            items.add(Number(input.substring(i, numberEndIndex).toInt()))
            i = numberEndIndex
        }
    }
    return items
}

fun indexOfClosing(input: String, start: Int): Int {
    var opened = 0
    for (i in start until input.length) {
        if (input[i] == ']') {
            if (opened == 1) {
                return i
            }
            opened--
        }
        if (input[i] == '[') {
            opened++
        }
    }
    throw IllegalArgumentException("closing not found")
}

