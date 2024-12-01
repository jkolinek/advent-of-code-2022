package task17bv2

import java.nio.file.Files
import java.nio.file.Path

private const val l = 100000L

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
    for(rockN in 0L .. 1000000000000L){
//        println(rockN)
        val rock = selectRock(rockN)
        while (true) {
            when (moves[(i % size).toInt()]) {
                '<' -> chamber.moveLeft(rock)
                '>' -> chamber.moveRight(rock)
            }
            i++
            if(!chamber.moveDown(rock)) {
//                println("$rock stopped")
                break
            }
        }
        if (rockN % 10000 == 0L) {
            print(" %.2f".format((rockN / 1000000000000.0) * 100))
            chamber.cleanUp()
        }

    }

//    chamber.printChamber()
    println("tall " + chamber.maxY)
}


