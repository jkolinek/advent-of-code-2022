package task17b

import java.math.BigDecimal
import java.math.RoundingMode
import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val moves = Files.readString(Path.of("src/main/resources/17.txt")).toList().filter { !it.isWhitespace() }

    val chamber = Chamber()

    fun selectRock(it: Long): Rock {
        when (it % 5) {
            0L -> return  Rock1(3, chamber.maxY + 4L)
            1L -> return  Rock2(3, chamber.maxY + 4L)
            2L -> return  Rock3(3, chamber.maxY + 4L)
            3L -> return  Rock4(3, chamber.maxY + 4L)
            4L -> return  Rock5(3, chamber.maxY + 4L)
        }
        throw IllegalArgumentException()
    }

    var i = 0L
    val size = moves.size.toLong()
    var lastMaxY = 0L
    for(rockN in 0L until 1000000000000L){
//        println(rockN)
        val rock = selectRock(rockN)
        while (true) {
            when (moves[(i % size).toInt()]) {
                '<' -> chamber.moveLeft(rock)
                '>' -> chamber.moveRight(rock)
            }
            i++
            if(!chamber.moveDown(rock)) {
                break
            }
        }
        if (rockN % 100000 == 0L) {
            chamber.cleanUp()
        }


        if (i % size == 0L && rockN > 0) {
            val heightDiff = chamber.maxY - lastMaxY
            lastMaxY = chamber.maxY
            val avg = chamber.maxY.toBigDecimal().divide(rockN.toBigDecimal(), 15, RoundingMode.DOWN)
            println("$rockN maxY ${chamber.maxY} heightDiff $heightDiff estimate $avg")
        }

    }

    chamber.printChamber()
    println("tall " + chamber.maxY)
}


