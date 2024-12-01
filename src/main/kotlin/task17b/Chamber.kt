package task17b

import kotlin.math.max

class Chamber {
    val fragments: MutableSet<Fragment> = mutableSetOf()
    var maxY = 0L

    fun moveRight(rock: Rock) {
        if (rock.maxX() == 7) {
            return
        }
        if (canMoveX(rock, +1)) {
            rock.x++
        }
        return
    }

    fun moveLeft(rock: Rock) {
        if (rock.minX() == 1) {
            return
        }
        if (canMoveX(rock, -1)) {
            rock.x--
        }
        return
    }

    fun moveDown(rock: Rock): Boolean {
        if (rock.y - 1L > maxY){
            rock.moveDown()
            return true
        }

        if (rock.minY() == 1L) {
            stop(rock)
            return false
        }

        val canMove = rock.fragments.stream()
            .noneMatch { fragments.contains(Fragment(it.x + rock.x, it.y + rock.y - 1L)) }
        if (canMove) {
            rock.moveDown()
            return true
        }
        stop(rock)
        return false
    }

    private fun stop(rock: Rock) {
        rock.fragments.forEach {
            fragments.add(Fragment(it.x + rock.x, it.y + rock.y))
        }
        maxY = max( maxY, maxY + 4 - rock.down + rock.height() - 1)
//        println("rock $rock stopped, maxY $maxY")
//        printChamber()
//        readLine()!!
    }

    private fun canMoveX(rock: Rock, xChange: Int): Boolean {
        if(rock.y > maxY) return true
        return rock.fragments.stream()
            .noneMatch { fragments.contains(Fragment(it.x + rock.x + xChange, it.y + rock.y)) }
    }

    fun printChamber() {
        for (y in maxY downTo 1L ) {
            for (x in 1..7) {
                val f = Fragment(x, y)
                if (fragments.contains(f)) {
                    print('#')
                } else {
                    print('.')
                }
            }
            println()
        }
    }

    fun cleanUp() {
        val maxY = maxY
        val iterator = fragments.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().y < maxY - 500L) {
                iterator.remove()
            }
        }
    }

}
