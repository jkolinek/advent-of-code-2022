package task13b

class Number(val value: Int) : Item {

    override fun toString(): String {
        return value.toString()
    }

    override fun compareTo(item: Item):Int {
        check(item is Number)
        return value.compareTo(item.value)
    }
}
