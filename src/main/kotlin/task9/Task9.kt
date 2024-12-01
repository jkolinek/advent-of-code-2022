package task9
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs
import kotlin.math.sign


fun main() {
    var head = 0 to 0
    var tail = 0 to 0
    val tailVisited = mutableSetOf(tail)

    fun moveYCloser(yDis: Int) {
        tail = tail.first to tail.second + yDis.sign
    }

    fun moveXCloser(xDis: Int) {
        tail = tail.first + xDis.sign to tail.second
    }

    fun moveTail() {
        val xDis = head.first - tail.first
        val yDis = head.second - tail.second

        if (abs(xDis) > 1) {
            moveXCloser(xDis)
            if (abs(yDis) > 0) {
                moveYCloser(yDis)
            }
        }
        if (abs(yDis) > 1) {
            moveYCloser(yDis)
            if (abs(xDis) > 0) {
                moveXCloser(xDis)
            }
        }
        tailVisited.add(tail)
        println("tail moved to $tail")
    }

    fun move(count: Int, headAction: (Pair<Int, Int>) -> Pair<Int, Int>) {
        repeat(count) {
            head = headAction(head)
            println("head moved to $head")
            moveTail()
        }
    }

    Files.lines(Path.of("src/main/resources/9.txt"))
        .map(String::trim)
        .map { it.split(" ") }
        .forEach { (dir, count) ->
            println("$dir $count")
            when (dir) {
                "U" -> move(count.toInt()) { head -> head.first to head.second + 1 }
                "D" -> move(count.toInt()) { head -> head.first to head.second - 1 }
                "R" -> move(count.toInt()) { head -> head.first + 1 to head.second }
                "L" -> move(count.toInt()) { head -> head.first - 1 to head.second }
            }
        }

    println(tailVisited)
    println(tailVisited.size)

}

