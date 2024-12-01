package task13

class Array(val items: List<Item>, var i: Int = 0) : Item {

    override fun toString(): String {
        return "$items"
    }

    override fun compareTo(otherArray: Item): Int {
        check(otherArray is Array)

        var i = 0
        while (i <= items.lastIndex) {
            val thisItem = items[i]
            if (i > otherArray.items.lastIndex) {
                return 1
            }
            val thatItem = otherArray.items[i]
            if (thisItem is Number && thatItem is Number) {
                val result = thisItem.compareTo(thatItem)
                if (result != 0) {
                    return result
                }
            }
            else {
                val result = wrapIfNumber(thisItem).compareTo(wrapIfNumber(thatItem))
                if (result != 0) {
                    return result
                }
            }
            i++
        }
        return items.lastIndex.compareTo(otherArray.items.lastIndex)
    }

    private fun wrapIfNumber(item: Item): Item {
        if (item is Number) {
            return Array(listOf(item))
        }
        return item
    }
}
