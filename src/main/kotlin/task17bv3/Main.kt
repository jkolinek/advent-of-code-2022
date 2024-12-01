package task17bv3

import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val moves = Files.readString(Path.of("src/main/resources/17.txt")).toList().filter { !it.isWhitespace() }

    val chamber = Chamber()

    fun selectRock(it: Int): Rock {
        when (it % 5) {
            0 -> return  Rock1(3, chamber.maxY() + 4L)
            1 -> return  Rock2(3, chamber.maxY() + 4L)
            2 -> return  Rock3(3, chamber.maxY() + 4L)
            3 -> return  Rock4(3, chamber.maxY() + 4L)
            4 -> return  Rock5(3, chamber.maxY() + 4L)
        }
        throw IllegalArgumentException()
    }

    var i = 0
    repeat(2022){
        val rock = selectRock(it)
        while (true) {
            when (moves[i % moves.size]) {
                '<' -> chamber.moveLeft(rock)
                '>' -> chamber.moveRight(rock)
            }
            i++
            if(!chamber.moveDown(rock)) {
                break
            }
        }
    }

    chamber.printChamber()
    println("tall " + chamber.maxY())
}


