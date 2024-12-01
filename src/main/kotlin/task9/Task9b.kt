package task9
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs
import kotlin.math.sign


fun main() {
    var knots = MutableList(10) { 0 to 0 }
    val tailVisited = mutableSetOf(0 to 0)

    fun moveYCloser(index: Int, yDis: Int) {
        knots[index] = knots[index].first to knots[index].second + yDis.sign
    }

    fun moveXCloser(index: Int, xDis: Int) {
        knots[index] = knots[index].first + xDis.sign to knots[index].second
    }

    fun moveTail(i: Int) {
        val head = knots[i - 1]
        val tail = knots[i]
        val xDis = head.first - tail.first
        val yDis = head.second - tail.second

        if (abs(xDis) > 1 && abs(yDis) > 1) {
            moveXCloser(i, xDis.sign)
            moveYCloser(i, yDis.sign)
        }
        else if (abs(xDis) > 1) {
            moveXCloser(i, xDis)
            if (abs(yDis) > 0) {
                moveYCloser(i, yDis)
            }
        }
        else if (abs(yDis) > 1) {
            moveYCloser(i, yDis)
            if (abs(xDis) > 0) {
                moveXCloser(i, xDis)
            }
        }
        if (i == 9) {
            tailVisited.add(knots[i])
            return
        }
        moveTail(i + 1)
    }

    fun move(count: Int, headAction: (Pair<Int, Int>) -> Pair<Int, Int>) {
        repeat(count) {
            knots[0] = headAction(knots[0])
            moveTail(1)
            println(knots)
        }
    }

    Files.lines(Path.of("src/main/resources/9.txt"))
        .map(String::trim)
        .map { it.split(" ") }
        .forEach { (dir, count) ->
            println("$dir $count")
            when (dir) {
                "U" -> move(count.toInt()) { it.first to it.second + 1 }
                "D" -> move(count.toInt()) { it.first to it.second - 1 }
                "R" -> move(count.toInt()) { it.first + 1 to it.second }
                "L" -> move(count.toInt()) { it.first - 1 to it.second }
            }
        }

    println(tailVisited)
    println(tailVisited.size)

}

