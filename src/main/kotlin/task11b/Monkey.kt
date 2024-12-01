package task11b

import java.util.LinkedList

class Monkey(items: MutableList<Item>, val operation: (Item) -> (Item), val testDivisibleBy: Long, val throwToIfTrue:Int,
                  val throwToIfFalse:Int, var inspects: Int = 0) {
    val items = LinkedList(items)
    override fun toString(): String {
        return "Monkey(inspects=$inspects, items=$items)"
    }


}
