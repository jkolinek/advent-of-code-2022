package task11b

import java.nio.file.Files
import java.nio.file.Path

val regex = """Monkey (?<index>\d+):
  Starting items: (?<items>[\d ,]+)
  Operation: new = old (?<operator>[+*]) (?<operand>.+)
  Test: divisible by (?<divider>\d+)
    If true: throw to monkey (?<throwToIfTrue>\d+)
    If false: throw to monkey (?<throwToIfFalse>\d+)""".toRegex()

fun main() {
    val monkeys: List<Monkey> = readInput()

    repeat(10000) {
        monkeys.forEach { monkey ->
            val itemIterator = monkey.items.iterator()
            while (itemIterator.hasNext()) {
                val item = itemIterator.next()
                monkey.inspects++
                val inspectedItem = monkey.operation(item)
                val throwToMonkey =
                    if (inspectedItem.isDivisibleBy(monkey.testDivisibleBy)) monkey.throwToIfTrue else monkey.throwToIfFalse
                monkeys[throwToMonkey].items.add(inspectedItem)
                itemIterator.remove()
            }
        }
    }
    println(monkeys)

    val sortedDesc = monkeys.sortedByDescending { monkey -> monkey.inspects }
//    println(sortedDesc)
    println(sortedDesc[0].inspects * sortedDesc[1].inspects)
}

fun operatorFromChar(charOperator: String): (Item, Long) -> Item {
    return when (charOperator) {
        "+" -> { item, b -> Item(item.base  + b, item.multipliers) }
        "*" -> {  item, b -> Item(item.base, item.multipliers.plus(b))  }
        else -> throw Exception("That's not a supported operator")
    }
}

fun readInput(): List<Monkey> {
    val input = Files.readString(Path.of("src/main/resources/11.txt"))
    var matchResult = regex.find(input)
    val monkeys: MutableList<Monkey> = mutableListOf()
    do {
        val groups = matchResult!!.groups;
        val items = groups["items"]!!.value.split(", ").stream()
            .map { Item(it.toLong()) }
            .toList()

        val monkey = Monkey(
            items,
            { old -> operatorFromChar(groups["operator"]!!.value).invoke(old, operand(groups, old.base)) },
            groups["divider"]!!.value.toLong(),
            groups["throwToIfTrue"]!!.value.toInt(),
            groups["throwToIfFalse"]!!.value.toInt()
        )
        monkeys.add(groups["index"]!!.value.toInt(), monkey)
        matchResult = matchResult.next()
    } while (matchResult != null)
    return monkeys
}

private fun operand(groups: MatchGroupCollection, old: Long): Long {
    val value = groups["operand"]!!.value
    if (value == "old") {
        return old
    }
    return value.toLong()
}


