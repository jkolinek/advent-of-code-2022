package task13b

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val packets = Files.lines(Path.of("src/main/resources/13.txt"))
        .map(String::trim)
        .filter(String::isNotBlank)
        .map { l -> Array(processInput(l)) }
        .toList()

    val packetsSorted = mutableListOf<Item>()
    packetsSorted.addAll(packets)
    val div1 = Array(listOf(Array(listOf(Number(2)))))
    val div2 = Array(listOf(Array(listOf(Number(6)))))
    packetsSorted.add(div1)
    packetsSorted.add(div2)
    packetsSorted.sort()

    println(packetsSorted)
    val index1 = packetsSorted.indexOf(div1)+ 1
    val index2 = packetsSorted.indexOf(div2) +1
    val score = index1 * index2
    println(score)
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

