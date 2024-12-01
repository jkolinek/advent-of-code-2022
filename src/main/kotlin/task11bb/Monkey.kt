package task11bb

import java.util.LinkedList

class Monkey(items: MutableList<Long>, val operation: (Long) -> (Long), val divider: Long, val throwToIfTrue:Int,
             val throwToIfFalse:Int, var inspects: Int = 0 ) {
    val items = LinkedList(items)
    override fun toString(): String {
        return "Monkey(inspects=$inspects, items=$items)"
    }


}
